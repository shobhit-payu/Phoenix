package utils

import service.ConfigService

/**
  * Created by pragya.mishra on 1/8/18.
  */
object MerchantUtil {

  def getMerchantSalt = {
    // a seperate db is maintained for storing salt
    // only saltv2 will be used
    // encrypted salt will be taken from that,  no need of transaction or merchant_param inlike php code
    val salt = "HKJSHK"
    //from merchant param
    val encryptUtil = EncryptionUtil.apply(ConfigService.getValueForKey("enc_cen_ltas"))
    encryptUtil.decrypt(salt)
  }

}
