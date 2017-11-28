package validators

import consts.PaymentRequest
import util.JsonToClassConverter

/**
  * Created by pragya.mishra on 11/24/17.
  */

object PaymentRequestValidation {

  def doValidation(request : String): Unit = {
    val sanitizedRequest = JsonToClassConverter.getSanitizedJsonString(request)
    val paymentRequest = JsonToClassConverter.getObject(sanitizedRequest, classOf[PaymentRequest]).asInstanceOf[PaymentRequest]
  }

}
