package validators

import model.PaymentRequest

/**
  * Created by pragya.mishra on 12/5/17.
  */
object ValidateVendorCheckSum {
  //payu paisa
  def validateChecksumForVendor (paymentRequest: PaymentRequest) : Unit = {
    /*
    $mercHash = $this->calculateChecksum($mercVars);

    // if checksum checking  is not require then set the colume checksum_check=0 of vender table
    if ($this->checksum_check==1 && $mercHash != $mercVars['hash']) {
      $e = new MerchantIntegrationException('Checksum Failed');
      $details = array('payu_hash' => $mercHash, 'type' => 'vendor');
      $e->setDetail($details);
      throw $e;
    }

    if( !$redirect ) {
      return true;
    }

    $paymentParts = json_encode(array());
    $productInfo = json_decode($mercVars['productinfo'], true);
    if(!empty($productInfo) && !empty($productInfo['paymentParts'])){
      $paymentParts = json_encode($productInfo['paymentParts']);
    }
    $paymentIdentifiers = array();
    if(!empty($productInfo) && !empty($productInfo['paymentIdentifiers'])){
      $paymentIdentifiers = $productInfo['paymentIdentifiers'];
    }

    $transactionIdentifier = array(array("field" => "Merchant Transaction Indentifier", "value" => $mercVars['txnid']));


    $paymentIdentifiers = json_encode(array_merge($paymentIdentifiers, $transactionIdentifier));
    //$mercVars['productinfo'] = null;
    $mercVars['hash'] = null;
    $txnDetails = json_encode($mercVars, JSON_HEX_AMP);
    $data = '&merchantId=' . $this->guid .'&totalAmount=' . $mercVars['amount']. '&paymentIdentifiers='.$paymentIdentifiers.'&paymentTypeId=null&paymentParts='.$paymentParts.'&paymentDescription=null&userId=null&txnDetails='.$txnDetails;
    $url = ConfigBase::get('payu_paisa_addpayment_url');
    $response = $this->curlCall($url, $data);
    if(!$response) {
      return false;
    }

    //add check for pageUrl and add logs also
    header("Location:".$response['pageUrl']. $response['guid']);
    gracefullExit();
    */
  }

  //payu pasia
  def calculateChecksumForVendor (paymentRequest: PaymentRequest) : Unit = {
    /*
    $salt = $this->salt;
     $version = isset($mercVars['api_version']) ? $mercVars['api_version'] : $this->getApiVersion();
     // merchant variables checksum validation start
     $mercHashVarsSeq = ConfigBase::get('merc_hash_vars_seq', $version);

     if ($mercHashVarsSeq) {
       $mercHashVarsSeq = explode('|', $mercHashVarsSeq);
     } else {
       $mercHashVarsSeq = array();
     }

     $mercHashString = "";
     foreach ($mercHashVarsSeq as $mercHashVar) {
       $mercHashString .= isset($mercVars[$mercHashVar]) ? $mercVars[$mercHashVar] : '';
       $mercHashString .= '|';
     }

     $mercHashString .= $salt ;

     /*
      * to include optional fields in hash calculation
     * @author: Himanshu
     */
     $mercOptionalHashVarsSeq = ConfigBase::get('merc_optional_hash_vars_seq');
     if($mercOptionalHashVarsSeq) {
     	$mercOptionalHashVarsSeq = explode('|', $mercOptionalHashVarsSeq);
     } else {
     	$mercOptionalHashVarsSeq = array();
     }

     foreach($mercOptionalHashVarsSeq as $optionalVar){
     	$mercHashString .= (isset($mercVars[$optionalVar]) && ($mercVars[$optionalVar]!="")) ? $mercVars[$optionalVar] . '|' : '';
     }
     if( $htmlEncode )
        $mercHashString = html_entity_decode($mercHashString);

     $mercHash = ibhash_algo($mercHashString);

     return $mercHash;
     */
  }

}
