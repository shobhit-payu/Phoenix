package util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
  * Created by pragya.mishra on 11/28/17.
  *
  * Utility to sanitize json and convert to class
  */

object JsonToClassConverter {

  val objectMapper = new ObjectMapper() with ScalaObjectMapper
  objectMapper.registerModule(DefaultScalaModule)

  /**
    * @param request Json to be sanitized
    * @return Sanitized Json with trimmed key values and lowercase keys
    */
  def getSanitizedJsonString(request : String) : String = {
    val requestMap : Map[String,String] = objectMapper.readValue[Map[String, String]](request)
    val sanitizedMap = requestMap.map { case (key, value) => key.trim.toLowerCase -> value.trim }
    objectMapper.writeValueAsString(sanitizedMap)
  }

  /**
    * @param request (Sanitized) Json
    * @param cc Class to which Json is to be casted
    * @return Object
    */
  def getObject(request : String, cc : Class[_]) : Any = {
    objectMapper.readValue(request, cc)
  }

}
