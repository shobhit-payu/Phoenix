package util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
  * Created by pragya.mishra on 11/28/17.
  */

object JsonToClassConverter {

  val objectMapper = new ObjectMapper() with ScalaObjectMapper
  objectMapper.registerModule(DefaultScalaModule)

  // sanitize key value : trim key-value and lowercase key
  def getSanitizedJsonString(str : String) : String = {
    val requestMap : Map[String,String] = objectMapper.readValue[Map[String, String]](str)
    val sanitizedMap = requestMap.map { case (key, value) => key.trim.toLowerCase -> value.trim }
    objectMapper.writeValueAsString(sanitizedMap)
  }

  //cast sanitized Json String to Object
  def getObject(request : String, cc : Class[_]): Any = {
    objectMapper.readValue(request, cc)
  }

}
