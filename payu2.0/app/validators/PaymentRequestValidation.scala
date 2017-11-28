package validators

import consts.PaymentRequest
import util.JsonToClassConverter

/**
  * Created by pragya.mishra on 11/24/17.
  */

object PaymentRequestValidation {

  /**
    * sanitizes and perform validation of request
    * @param request Payment request (json) from merchant
    */
  def doValidation(request : String): Unit = {
    val sanitizedRequest = JsonToClassConverter.getSanitizedJsonString(request)
    val paymentRequest = JsonToClassConverter.getObject(sanitizedRequest, classOf[PaymentRequest]).asInstanceOf[PaymentRequest]
  }

  /**
    * checks if the request has all the mandatory params
    * @param paymentRequest Payment request object
    */
  def checkMandatoryParams(paymentRequest: PaymentRequest): Unit = {
    //get config value
  }

}
