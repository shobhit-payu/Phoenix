package validators

import model.PaymentRequest
import util.JsonToClassConverter

/**
  * Created by pragya.mishra on 11/24/17.
  */

object PaymentRequestValidation {

  /**
    * sanitizes and perform validation of request
    *
    * @param request Payment request (json) from merchant
    */
  def doValidation(request : String): Unit = {
    val sanitizedRequest = JsonToClassConverter.getSanitizedJsonString(request)
    var paymentRequest = JsonToClassConverter.getObject(sanitizedRequest, classOf[PaymentRequest]).asInstanceOf[PaymentRequest]
    sanitizeUrls(paymentRequest)
    //processingForDomesticBin(paymentRequest)
    //checkMandatoryParams(paymentRequest)
  }

  /**
    * Urlencode the urls
    * @param paymentRequest
    */
  def sanitizeUrls(paymentRequest: PaymentRequest) : Unit = {
    val findAmpersand = "&".r
    findAmpersand.replaceAllIn(paymentRequest.furl, "%26")
    findAmpersand.replaceAllIn(paymentRequest.surl, "%26")
    findAmpersand.replaceAllIn(paymentRequest.curl, "%26")
    findAmpersand.replaceAllIn(paymentRequest.codurl, "%26")
    findAmpersand.replaceAllIn(paymentRequest.touturl, "%26")
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
    //$mendatoryParams = ConfigBase::get("merc_mand_vars");               //key|txnid|amount|productinfo|surl|hash
    /*$mendatoryParams = explode('|', $mendatoryParams);
    $otherMandParams = array('firstname','email','phone');
    $mendatoryParams = array_merge($mendatoryParams, $otherMandParams);
    $build  = ConfigBase::get('build');
    $checkMan = false;
    $payuId = $this->getRequestParam( 'payuId', null );
    $txnId  = $this->getRequestParam( "txnid", null );
    $this->cleanManRequestParams();
    $amount= $this->getManRequestParam( "amount", null );

    $base_payuid = $this->getRequestParam( "base_payuid", null );
    $base_merchantid = $this->getRequestParam( "base_merchantid", null );

    // If id generator flag is Active : Check if the received PayuId already exists or is a new transcation.
    if ( ConfigBase::get( "id_gen_flag" ) && ! $payuId ) {
      $error = new payuExceptionError(payuExceptionError::MANDATORY_PARAMETER_PAYUID_MISSING, payuExceptionError::PAYMENT_FLOW_EXCEPTION, __CLASS__, __FUNCTION__);
      $error->execute();
      //throw new PaymentFlowException( "Mandatory parameter payuId missing." );
    }
    // check if txnID and other mandatory params are empty then throw a merchant integration exception
    $eMsg = "";
    if ($build == 'Production'){
    $checkMan = empty($txnId) || !is_numeric($amount) ;
  }
    else {
    list($checkMan, $eMsg) = $this->testTransaction($mendatoryParams, $eMsg);
  }

    if ( !is_numeric($amount) ) {
      $error = new payuExceptionError(payuExceptionError::INVALID_AMOUNT, payuExceptionError::MERCHANT_INTEGRATION_EXCEPTION, __CLASS__, __FUNCTION__,array($eMsg));
      $error->execute();
      //throw new MerchantIntegrationException( "Mandatory parameter txnId missing for .".$decoded_mihpayid );
    }

    if ( $checkMan ) {
      $error = new payuExceptionError(payuExceptionError::MANDATORY_PARAMETER_TXNID_MISSING, payuExceptionError::MERCHANT_INTEGRATION_EXCEPTION, __CLASS__, __FUNCTION__,array($eMsg));
      $error->execute();
      //throw new MerchantIntegrationException( "Mandatory parameter txnId missing for .".$decoded_mihpayid );
    }
    moneyUtility::checkBasePayuidMerchantId($base_payuid, $base_merchantid);
  */}

  /**
    *
    * @param paymentRequest
    */
  def validateTransaction ( paymentRequest: PaymentRequest ) : Unit = {
    if (paymentRequest.key.isEmpty) {
      //throw MERCHANT_KEY_MISSING exception
    }
    /*
    //if merchant key missing throw an exception
    if ( ! isset( $this->merc_vars['key'] ) ) {
    $error = new payuExceptionError(payuExceptionError::MERCHANT_KEY_MISSING, payuExceptionError::MERCHANT_INTEGRATION_EXCEPTION, __CLASS__, __FUNCTION__);
    $error->execute();
    //throw new MerchantIntegrationException( 'Merchant key missing in Request' );
  }
    // service provider is payu paisa then create a vendor and
    // go to the paisa login page as long as check sum is validated
    else if ( isset( $this->merc_vars['service_provider'] ) && ($this->merc_vars['service_provider'] == "payu_paisa") ) {
    $vendor = new Vendor( null, $this->merc_vars['key'], $this->merc_vars['service_provider'] );
    $isValid = $vendor->validateCheckSum( $this->merc_vars );
    if($isValid === false) {
      $error = new payuExceptionError(payuExceptionError::PAYU_PAISA_ADDPAYMENT_URL_FAILED, payuExceptionError::EXCEPTION, __CLASS__, __FUNCTION__);
      $error->execute();
      //throw new Exception('Add Payment Call to payu_paisa_addpayment_url failed.');
    }
  }
    // case when the merchant key is there and the service provider is not paisa then
    else if ( !empty($this->merc_vars['vendor_id']) && isset( $this->merc_vars['service_provider'] ) &&
    !empty($this->merc_vars['service_provider']) &&
    $this->merc_vars['service_provider'] == 'payumoney_s2s' )
    {
      Logger::log('Calculating the checksum using vendor key and salt for vendor ID ' . $this->merc_vars['vendor_id'], 'INFO', 'PAISA S2S Flow',0,false);
      $vendor = new Vendor($this->merc_vars['vendor_id']);
      $isValid = $vendor->validateCheckSum( $this->merc_vars, false );

      //overriding the `key` as merchant Key after mapping from vendor table
      $vendorId = $this->merc_vars['vendor_id'];
      $actualMerchantKey = PayuUtil::getMerchantKeyfromVendor($vendorId);

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
