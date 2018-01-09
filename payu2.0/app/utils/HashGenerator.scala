package utils
import java.security.MessageDigest

import org.apache.commons.codec.binary.Base64

/**
  * Created by pragya.mishra on 1/9/18.
  */

object HashGenerator {

  def generate(str: String, t: String): String = {
    val strBytes = Base64.decodeBase64(str)
    val checksum = MessageDigest.getInstance(t) digest strBytes
    checksum.map("%02X" format _).mkString
  }

  /*def sha1(bytes: Array[Byte]): String = {
    val digest = MessageDigest.getInstance("SHA-1")
    digest.reset()
    digest.update(bytes)
    digest.digest().map(0xFF & _).map { "%02x".format(_) }.foldLeft("") { _ + _ }
  }*/

}