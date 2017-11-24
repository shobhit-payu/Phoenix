package validators

import consts.PaymentRequest
import net.liftweb.json._

/**
  * Created by pragya.mishra on 11/24/17.
  */

object PaymentRequestValidation {

  implicit  val formats = DefaultFormats

  def doValidation(request : String): Unit = {
    // getSanitizedJsonString(request)
    //getPaymentRequestObject(request).asInstanceOf[PaymentRequest]
  }

  /* sanitize key value : trim and lower case key-value
  def getSanitizedJsonString(str : String) : String {

  }*/

  //converts Json String to PaymentRequest case object
  def getPaymentRequestObject(request : String): Any= {
    val json = parse(request)
    json.extract[PaymentRequest]
  }

}
