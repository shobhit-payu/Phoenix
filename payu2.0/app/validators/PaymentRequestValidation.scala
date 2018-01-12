package validators

import scala.util.{Success, Failure}
import consts.PayuException
import exception.PaymentFlowException
import model.PaymentRequest
import utils.{IsNumericString, CaseClassToMapConverter, ImplementStrategy, JsonToClassConverter}
import service.ConfigService

/**
  * Created by pragya.mishra on 11/24/17.
  */

object PaymentRequestValidation {

  /**
    * sanitizes and perform validation of request
    *
    * @param request Payment request (json) from merchant
    * @todo make small different classes for each functions, move generics to utils like validationUtils
    */
  def doValidation(request : String): Unit = {
    val sanitizedRequest = JsonToClassConverter.getSanitizedJsonString(request)
    var paymentRequest = JsonToClassConverter.getObject(sanitizedRequest, classOf[PaymentRequest]).asInstanceOf[PaymentRequest]
    sanitizeUrls(paymentRequest)
    val merchantParams = List("si_enabled", "s2s_enabled")
    ImplementStrategy.executeAfterValidation(paymentRequest,merchantParams)
    ImplementStrategy.executeAfterMaf(paymentRequest,merchantParams)
    //processingForDomesticBin(paymentRequest)
    //checkMandatoryParams(paymentRequest)
    //validateTransaction(paymentRequest)
    ValidateChecksum.validateCheckSum(paymentRequest)
  }

  /**
    * Urlencode the urls
    *
    * @param paymentRequest
    */
  def sanitizeUrls(paymentRequest: PaymentRequest) : Unit = {
    val findAmpersand = "&".r
    if (paymentRequest.furl !=  null && !paymentRequest.furl.isEmpty()) {
      paymentRequest.furl = findAmpersand.replaceAllIn(paymentRequest.furl, "%26")
    }
    if (paymentRequest.surl !=  null && !paymentRequest.surl.isEmpty()) {
      paymentRequest.surl = findAmpersand.replaceAllIn(paymentRequest.surl, "%26")
    }
    if (paymentRequest.curl !=  null && !paymentRequest.curl.isEmpty()) {
      paymentRequest.curl = findAmpersand.replaceAllIn(paymentRequest.curl, "%26")
    }
    if (paymentRequest.codurl !=  null && !paymentRequest.codurl.isEmpty()) {
      paymentRequest.codurl = findAmpersand.replaceAllIn(paymentRequest.codurl, "%26")
    }
    if (paymentRequest.touturl !=  null && !paymentRequest.touturl.isEmpty()) {
      paymentRequest.touturl = findAmpersand.replaceAllIn(paymentRequest.touturl, "%26")
    }
  }

  /**
    *
    * @param paymentRequest
    */
  def processingForDomesticBin(paymentRequest: PaymentRequest) :  Unit = {
    //In case if name is empty or null then in case of domestic or unkown card set it to default i.e. payu
    if(paymentRequest.ccnum.isEmpty){
      //$result = binHandler::checkDomesticBin($getSanParams['ccnum']);
      //$bin_found_details = binHandler::getBinType($result);
      //$is_international = ($bin_found_details == binHandler::CARD_TYPE_INTERNATIONAL  ) ? true : false;
      //if(is_international){
      // Setting global POST param to default value 'payu'
      //paymentRequest.ccnum = "payu"
      //}
    }
  }

  /**
    * checks if the request has all the mandatory params
 *
    * @param paymentRequest Payment request object
    */
  def checkMandatoryParams(paymentRequest: PaymentRequest) : Unit = {
    val mandatoryParams : String = ConfigService.getValueForKey("merc_mand_vars")
    val otherMandatoryParams : Array[String] = Array("firstname","email","phone")
    var mandatoryParamsArray : Array[String] = mandatoryParams.split("""\|""")
    mandatoryParamsArray = mandatoryParamsArray ++ otherMandatoryParams
    val paymentRequestMap = CaseClassToMapConverter.getMap(paymentRequest)
    for (mandateKey <- mandatoryParamsArray) {
      val value = paymentRequestMap.get(mandateKey)
      if (value ==  null || value.isEmpty) {
        throw new PaymentFlowException(PayuException.MISSING_MANDATORY_PARAMETERS.exceptionCode,PayuException.MISSING_MANDATORY_PARAMETERS.exceptionMessage)
      }
    }
    if (!IsNumericString.check(paymentRequest.amount)) {
      throw new PaymentFlowException(PayuException.INVALID_AMOUNT.exceptionCode,PayuException.INVALID_AMOUNT.exceptionMessage)
    }
    if (!IsNumericString.check(paymentRequest.phone)) {
      throw new PaymentFlowException(PayuException.INVALID_PHONE.exceptionCode,PayuException.INVALID_PHONE.exceptionMessage)
    }
    if (!paymentRequest.email.contains("@")) {
      throw new PaymentFlowException(PayuException.INVALID_EMAIL.exceptionCode,PayuException.INVALID_EMAIL.exceptionMessage)
    }
  }


  /**
    *
    * @param paymentRequest
    */
  def validateTransaction ( paymentRequest: PaymentRequest ) : Unit = {
    if (paymentRequest.key.isEmpty) {
      throw new PaymentFlowException(PayuException.MISSING_MERCHANT_KEY.exceptionCode,PayuException.MISSING_MERCHANT_KEY.exceptionMessage )
    }
      /*

      //overriding the key
      Logger::log('Merchant Key for vendor Id ' . $vendorId  . ' is ' . $actualMerchantKey, 'INFO', 'INFO',0,false);
      $this->merc_vars['key'] = $actualMerchantKey;
      $this->merchant = new Merchant( 0, $this->merc_vars['key'] );
      if ( ! $this->merchant->active ) {
        $this->fireKeyInactiveError();
        //throw new PaymentFlowException( 'Inactive Merchant' );
      }
    } else {
    // create the merchant object
    $this->merchant = new Merchant( 0, $this->merc_vars['key'] );
    // if not active throw an exception
    if ( ! $this->merchant->active ) {
      $this->fireKeyInactiveError();
      //throw new PaymentFlowException( 'Inactive Merchant' );
    }
    /*
          *  For some Payu Paisa Merchants who are not willing to change their Payu Integration, this flow is implemented.
          *  If for a merchant, payu_paisa_merchant_id is set in merchant params table,
           * It will be treated as a Payu Paisa Vendor and will be sent to Vendor Flow.
          */
    if(!empty($this->merchant->payu_paisa_merchant_id) && $this->merchant->payu_paisa_merchant_id != 0 && empty($this->merc_vars['vendor_id'])){
      $vendorId = $this->merchant->payu_paisa_merchant_id;
      $vendor_payu_paisa = new Vendor($vendorId);
      $isValid_payu_paisa = $vendor_payu_paisa->validateCheckSum( $this->merc_vars );
      return;
    }
    // validate the checksum
    $isValid = $this->merchant->validateCheckSum( $this->merc_vars );

 }


    $this->merc_vars['saltUsed'] = $this->merchant->saltUsed;

    /*
         * Cleaning data once the checksum has been verifiied
         * Urls are first urlencoded, then cleaned and then urldecoded
         * This is so '&' in urls in not overwritten by htmlentities
        */



    // urlencode
    $postParams['surl'] = str_replace( '&', '%26', $postParams['surl'] );
    $postParams['furl'] = str_replace( '&', '%26', $postParams['furl'] );
    $postParams['curl'] = str_replace( '&', '%26', $postParams['curl'] );
    $postParams['codurl'] = str_replace( '&', '%26', $postParams['codurl'] );
    $postParams['touturl'] = str_replace( '&', '%26', $postParams['touturl'] );

    // clean
    $postParams = cleanGlobalsArray( $postParams );

    // urldecode
    $postParams['surl'] = urldecode( $postParams['surl'] );
    $postParams['furl'] = urldecode( $postParams['furl'] );
    $postParams['curl'] = urldecode( $postParams['curl'] );
    $postParams['codurl'] = urldecode( $postParams['codurl'] );
    $postParams['touturl'] = urldecode( $postParams['touturl'] );
    //case to check wallet transaction has taken place
    //in that case set payment_source to wallet
    if( !empty($postParams['base_payuid']) && !empty($postParams['base_merchantid']) ){
    $postParams['payment_source'] = "wallet";
    Logger::log("payment_source wallet added for the current wallet transaction","INFO","WALLETTXN",0,false);
  }

    $bankcodePayuWCheck = trim(strtoupper($postParams['bankcode']));
    //check if ibibo code posted is of wallet payu then override the mode to WALLET
    if( $bankcodePayuWCheck == DataArray::WALLET_PAYU_IBIBO_CODE  ){
      Logger::log("Changing the mode from ".$postParams['pg']." to ".DataArray::WALLET_PAYU_MODE_CODE." because of bankcode being posted as ".DataArray::WALLET_PAYU_IBIBO_CODE,"INFO","WALLETTXN",0,false);
      $postParams['pg'] = DataArray::WALLET_PAYU_MODE_CODE;
    }

    if(!empty($postParams['SIcredential'])) {
    $postParams['si'] = 1;
    $postParams['user_credentials'] = $postParams['SIcredential'];
  }

    if(!empty($postParams['si']) && $this->merchant->allow_standing_instruction && !empty($postParams['user_credentials'])){
    if($postParams['si'] == 1){
      if(empty($postParams['SIcredential'])) {
        $postParams['payment_source'] = 'sist';// payment_source == si for transactions for which standing instruction is to be done
      }
      $enforce_paymethod = $postParams['enforce_paymethod'];
      /*
               * enforce cards if DC/CC available as SI can only be done on CC
               * allowing DC as CC can be entered in DC tab
               * If CC is not entered, card will not be stored.
               *
               */
      if(!empty($enforce_paymethod)){
        $enforce_paymethod = explode('|', $enforce_paymethod);
        if(in_array('creditcard', $enforce_paymethod) || in_array('CC', $enforce_paymethod) || in_array('debitcard', $enforce_paymethod)){
          if($this->merchant->getMerchantParam('enable_si_dc'))
          $postParams['enforce_paymethod'] = 'CC|VISA|MAST|MAES|RUPAY';
          else
          $postParams['enforce_paymethod'] = 'CC';
        }
      }
      elseif(empty($enforce_paymethod)){
        if($this->merchant->getMerchantParam('enable_si_dc'))
        $postParams['enforce_paymethod'] = 'CC|VISA|MAST|MAES|RUPAY';
        else
        $postParams['enforce_paymethod'] = 'CC';
      }
    }if($postParams['si'] == 2)
    $postParams['payment_source'] = 'sinst';
    Logger::log( "SI Transaction : params - " . serialize( $postParams ), "INFO", "SI TRANSACTION" );
  }

    if(!empty($postParams['SIcredential'])) {
    $postParams['payment_source'] = 'si_invoice';
  }

    //in case transaction is initiated by payumoney(p1)
    if(isset ($postParams['service_provider']) && !empty($postParams['service_provider']) && trim($postParams['service_provider']) == 'payumoney_s2s') {
    $postParams['payment_source'] = 'payumoney';
  }

    //set the merc_vars post clean up
    foreach ( $postParams as $key => $value ) {
    $this->merc_vars[strtolower( trim( $key ) )] = $value;
  }
    // If this is a compulsive merchant hence setting the pg and ibibocode which in turn will direct to paisa login page.
    $payumoney_checkout = $this->merchant->payumoney_checkout;
    if ( ! empty( $payumoney_checkout ) ) {
      Logger::log( 'Compulsive Merchant Case hence overriding the ibibocode and mode ','INFO','MISC',0,false );
      $this->setCompulsiveMerchantDetails();
    }
  */}

}
