package utility

import com.google.gson.Gson
import consts.PaymentRequest
import net.liftweb.json._

/**
  * Created by pragya.mishra on 11/23/17.
  */

object JsonConverter {
  val gson: Gson = new Gson
  implicit  val formats = DefaultFormats

  //object to JSON String
 def toJsonString(value: Object): String = {
    gson.toJson(value)
  }

 // JSON String to Object
  def fromJsonString[T](value: String, c: Class[_]): Any = {
    gson.fromJson(value, c)
  }

  //convert JSON string to paymentRequest case object
  def getPaymentRequest(str : String) : Any = {
    val json = parse(str)
    return json.extract[PaymentRequest]
  }

  }
