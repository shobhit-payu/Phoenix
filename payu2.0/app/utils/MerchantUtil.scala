package utils

import cache.ConfigCache

/**
  * Created by pragya.mishra on 1/8/18.
  */
object MerchantUtil {

  def getMerchantSalt = {
    // a seperate db is maintained for storing salt
    // only saltv2 will be used
    // encrypted salt will be taken from that,  no need of transaction or merchant_param inlike php code
    val salt = "TEST STRING"
    //from merchant param
    //val encryptUtil = EncryptionUtil.apply("1234567812345678123456781234567812345678121")
    val encryptUtil = EncryptionUtil.apply(ConfigCache.doGet("enc_cen_ltas"))
    val s = encryptUtil.encrypt(salt)
    println("The encrypted value " + s)
    println("The decrypted value "+ encryptUtil.decrypt(s))
  }

}
