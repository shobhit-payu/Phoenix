package validators

import model.PaymentRequest

/**
  * Created by pragya.mishra on 12/5/17.
  */

object ValidateChecksum {
  //Normal Merchants
  def validateCheckSum(paymentRequest: PaymentRequest): Unit = {
    val mercHash = getMerchantCheckSum(paymentRequest)
    /*
  $mercHash = $this->getMerchantCheckSum($mercVars);
  //Making hash a non mandatory parameter
  $params = $this->getMerchantParams();
  if(isset($params['light_hash']) && $params['light_hash'] = 1){
    return true;
  }

  if (!in_array($mercVars['hash'], $mercHash)) {
    $eMsg = '#~#<span style="text-align: justify">Please ensure that the <b>hash</b> used in transaction request is calculated using the correct formula.';
    //showing hash formula if not in production
    $build  = ConfigBase::get('build');
    if ($build != 'Production'){
      $apiVersion = isset( $mercVars['api_version'] ) ? $mercVars['api_version'] : $this->getApiVersion();
      $merchantHashVersion = isset( $mercVars['version'] ) ? $mercVars['version'] : null;
      $mercHashVarsSeq = $this->getHashVarsSequence( $apiVersion, $merchantHashVersion );
      $hashSalt = getMerchantSALT($this->SALT);
      $varSeqStr = $mercHashVarsSeq;
      $udfsToRemove = array("udf6","udf7", "udf8", "udf9", "udf10");
      $varSeqStr = str_replace($udfsToRemove,"", $varSeqStr);
      if ( $mercHashVarsSeq ) {
        $mercHashVarsSeq = explode( '|', $mercHashVarsSeq );
      } else {
        $mercHashVarsSeq = array ();
      }
      $mercHashString = "";
      foreach ( $mercHashVarsSeq as $mercHashVar ) {
        $mercHashString .= isset( $mercVars[$mercHashVar] ) ? $mercVars[$mercHashVar] : '';
        $mercHashString .= '|';
      }
      $mercHashString .= $hashSalt;

      $eMsg .= ' Please note the correct formula for calculating the value of <b>hash</b>:<br>';
      $eMsg .= '<b>sha512('.$varSeqStr.'|SALT)</b></p>';
      $eMsg .= '<p style="font-size:13px">Based on above formula and applying for this transaction, hash should be calculated as mentioned below :';
      $eMsg .= '<br><b>hash</b> = <span style="font-size:12px">sha512('.$mercHashString.') = '.$mercHash.'';
        $eMsg .= '<br><div style="font-size: 13px;padding: 0 150px; padding: 0 150px;">As seen above, correct <b>hash</b> value should have been &#45; <span style="font-size:12px">'.$mercHash.'</span><div>';
          $eMsg .= '<br>But the <b>hash</b> posted in the transaction request from your end was &#45; <span style="font-size:12px">'.$mercVars['hash'].'</span></b>';
          $eMsg .= '<br><br>Please re-initiate a transaction with correctly calculated <b>hash</b> value.</span></p></div>';
          }
          $error = new payuExceptionError(payuExceptionError::CHECKSUM_FAILED, payuExceptionError::PAYMENT_FLOW_EXCEPTION, __CLASS__, __FUNCTION__,array($eMsg));
          $error->execute(true,false);
          }

          $usedSalt = array_search($mercVars['hash'], $mercHash);
          Logger::log('Salt Used for merchant ' . $this->id . ' is ' . $usedSalt, 'SALT', 'SALT');

          $this->saltUsed = DataArray::$saltVersionVsName[$usedSalt];
          return true;
          */
  }

  def getMerchantCheckSum(paymentRequest: PaymentRequest): Unit = {
    val mercHashVarsSeq = getHashVarsSequence(paymentRequest.apiVersion, paymentRequest.version)
    /*
   $apiVersion = isset( $mercVars['api_version'] ) ? $mercVars['api_version'] : $this->getApiVersion();
   $merchantHashVersion = isset( $mercVars['version'] ) ? $mercVars['version'] : null;
   $mercHashVarsSeq = $this->getHashVarsSequence( $apiVersion, $merchantHashVersion );

   $saltArr = $this->getRelevantSalt(null, null, null, true);
   $hashSaltV1 = $saltArr[0];
   $hashSaltV2 = $saltArr[1];

   if ( $mercHashVarsSeq ) {
     $mercHashVarsSeq = explode( '|', $mercHashVarsSeq );
   } else {
     $mercHashVarsSeq = array ();
   }

   $mercHashString = "";
   foreach ( $mercHashVarsSeq as $mercHashVar ) {
     $mercHashString .= isset( $mercVars[$mercHashVar] ) ? $mercVars[$mercHashVar] : '';
     $mercHashString .= '|';
   }
   $mercHashStringV1 = $mercHashString . $hashSaltV1;
   $mercHashStringV2 = $mercHashString . $hashSaltV2;

   /*
        * to include optional fields in hash calculation
        * @author: Himanshu
        */
   $mercOptionalHashVarsSeq = ConfigBase::get( 'merc_optional_hash_vars_seq' );
   if ( $mercOptionalHashVarsSeq ) {
     $mercOptionalHashVarsSeq = explode( '|', $mercOptionalHashVarsSeq );
   } else {
     $mercOptionalHashVarsSeq = array ();
   }

   foreach ( $mercOptionalHashVarsSeq as $optionalVar ) {
     $mercHashStringV1 .= (isset( $mercVars[$optionalVar] ) && ($mercVars[$optionalVar] != "")) ? '|' . $mercVars[$optionalVar] : '';
     $mercHashStringV2 .= (isset( $mercVars[$optionalVar] ) && ($mercVars[$optionalVar] != "")) ? '|' . $mercVars[$optionalVar] : '';
   }
   if( $flag ){
     $mercHashStringV1 = html_entity_decode($mercHashStringV1);
     $mercHashStringV2 = html_entity_decode($mercHashStringV2);
   }

   $newHashV1 = ibhash_algo( $mercHashStringV1 );
   $newHashV2 = ibhash_algo( $mercHashStringV2 );
   Logger::log( "Merchant Hash String :" . $mercHashString . 'and new Hash :' . $newHashv1 . ' and ' . $newHashV2);
   return array('v1' => $newHashV1, 'v2' => $newHashV2);
   */
  }

  def getHashVarsSequence(apiVersion: String, merchantHashVersion: String): Unit = {

    /*
  $merc_hash_vars_seq = ConfigBase::get( 'merc_hash_vars_seq', $apiVersion );

  if ( $merchantHashVersion && $merchantHashVersion > 0 ) {
    $hashKey = "merc_hash_vars_seq_" . trim( $merchantHashVersion );
    if ( isset( $this->$hashKey ) ) $merc_hash_vars_seq = $this->$hashKey;
  }
  return $merc_hash_vars_seq;
  */
  }

}