package utils

import java.security.SecureRandom

import org.apache.commons.codec.binary.Base64
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher
import org.bouncycastle.crypto.modes.CBCBlockCipher
import org.bouncycastle.crypto.engines.RijndaelEngine
import org.bouncycastle.crypto.paddings.ZeroBytePadding
import org.bouncycastle.crypto.params._
/**
  * Created by pragya.mishra on 1/8/18.
  */

class EncryptionUtil(keyBase64: String) {
  private val keyBytes = Base64.decodeBase64(keyBase64)
  private var ivBytes = new Array[Byte](256)
  val random : SecureRandom = SecureRandom.getInstance("SHA1PRNG")
  random.nextBytes(ivBytes)


  def encrypt(message: String): String = {
    val cipher : PaddedBufferedBlockCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new RijndaelEngine(256)), new ZeroBytePadding())
    val keySize = keyBytes.length
    val ivAndKey : ParametersWithIV = new ParametersWithIV(new KeyParameter(keyBytes, 0, keySize), ivBytes, 0, keySize)
    cipher.init(true, ivAndKey)
    val messageBytes = message.getBytes("UTF-8")
    val encrypted  = new Array[Byte](cipher.getOutputSize(messageBytes.length))
    val oLen = cipher.processBytes(messageBytes, 0, messageBytes.length, encrypted, 0)
    cipher.doFinal(encrypted, oLen)
    Base64.encodeBase64String(encrypted)
  }

  def decrypt(inputBase64: String): String = {
    val cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new RijndaelEngine(256)), new ZeroBytePadding())
    val keySize = keyBytes.length
    val ivAndKey = new ParametersWithIV(new KeyParameter(keyBytes, 0, keySize), ivBytes, 0, keySize)
    cipher.init(false, ivAndKey)
    val messageBytes = Base64.decodeBase64(inputBase64)
    val decrypted  = new Array[Byte](cipher.getOutputSize(messageBytes.length))
    val oLen = cipher.processBytes(messageBytes, 0, messageBytes.length, decrypted, 0)
    cipher.doFinal(decrypted, oLen)

    val zeroTerminationIndex = decrypted.indexOf(0)
    new String(decrypted, 0, zeroTerminationIndex, "UTF-8")
  }
}

object EncryptionUtil {
  def apply(keyBase64: String) = new EncryptionUtil(keyBase64)
}