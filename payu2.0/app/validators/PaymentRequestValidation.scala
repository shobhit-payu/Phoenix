package validators

import consts.PaymentRequest
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
  * Created by pragya.mishra on 11/24/17.
  */

object PaymentRequestValidation {

  def doValidation(request : String): PaymentRequest = {
    // getSanitizedJsonString(request)
    getPaymentRequestObject(request)
  }

  /* sanitize key value : trim and lower case key-value
  def getSanitizedJsonString(str : String) : String {

  }*/

  def getPaymentRequestObject(request : String): PaymentRequest= {
    val objectMapper = new ObjectMapper() with ScalaObjectMapper
    objectMapper.registerModule(DefaultScalaModule)
    objectMapper.readValue[PaymentRequest](request)
  }

}
