package consts

/**
  * Created by pragya.mishra on 11/29/17.
  */

sealed trait PayuException {def exceptionCode: String; def exceptionMessage: String}

object PayuException {

  case object MISSING_MERCHANT_KEY extends PayuException {
    val exceptionCode = "EX114";
    val exceptionMessage = "Merchant key missing in Request"
  }

}
