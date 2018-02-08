package validators

import cache.ConfigCache
import consts.{HashAlgo, PayuException}
import exception.PaymentFlowException
import model.PaymentRequest
import utils.{HashGenerator, CaseClassToMapConverter, MerchantUtil}

/**
  * Created by pragya.mishra on 12/5/17.
  */

object ValidateChecksum {
  //Normal Merchants

  def validateCheckSum(paymentRequest: PaymentRequest): Unit = {
    if (getMerchantCheckSum(paymentRequest) != paymentRequest.hash ) {
      throw new PaymentFlowException(PayuException.CHECKSUM_FALIED.exceptionCode,PayuException.CHECKSUM_FALIED.exceptionMessage)
    }
  }

  def getMerchantCheckSum(paymentRequest: PaymentRequest): Unit = {
    val mercHashVarsSeq = ConfigCache.doGet("merc_hash_vars_seq")
    val mercHashVarsArray : Array[String] = mercHashVarsSeq.split("""\|""")
    val paymentRequestMap = CaseClassToMapConverter.getMap(paymentRequest)
    var mercHashVarsString : String = null
    for (key <- mercHashVarsArray) {
        mercHashVarsString = mercHashVarsString + paymentRequestMap.get(key)  + "|"
    }
    mercHashVarsString = mercHashVarsString + MerchantUtil.getMerchantSalt
    HashGenerator.generate(mercHashVarsString, HashAlgo.SHA_512)
  }

}