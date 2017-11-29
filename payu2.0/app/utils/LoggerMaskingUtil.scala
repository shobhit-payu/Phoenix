package utils

import java.util.regex.{Matcher, Pattern}

import scala.util.control.Breaks._
import consts.{PayuConst, LoggerConst}

/**
  * Created by hemant.agrawal on 22/11/17.
  * This object is for mask logging message
  */
object LoggerMaskingUtil {

  def doAllMaskingBeforeLogging(message : String) : String = {
    var maskMessage = PayuConst.STRING_INITIALIZER

    maskMessage = maskCardBeforeLogging(message)
    maskMessage = maskCVVBeforeLogging(maskMessage)
    maskMessage = maskExpiryMonthBeforeLogging(maskMessage)
    maskMessage = maskExpiryYearBeforeLogging(maskMessage)
    maskMessage = maskEmail(maskMessage)

    return maskMessage
  }

  def maskCardBeforeLogging(message : String) : String = {
    var ccnum = PayuConst.STRING_INITIALIZER
    var maskedCard = PayuConst.STRING_INITIALIZER
    var maskedMessage = message
    var maskDone : Boolean = false
    breakable {
      for ((card, regex) <- LoggerConst.CARD_REGEX) {
        val pattern: Pattern = Pattern.compile(regex)
        val matcher: Matcher = pattern.matcher(message)
        while (matcher.find()) {
          ccnum = message.substring(matcher.start(), matcher.end())
          maskedCard = ccnum.substring(0, 4) + card + ccnum.substring(ccnum.length - 4, ccnum.length) + "(" + ccnum.length + ")"
          maskedMessage = message.replaceAll(regex, maskedCard)
          maskDone = true
        }
        if (maskDone) {
          break
        }
      }
    }
    return maskedMessage
  }

  def maskCVVBeforeLogging(message : String) : String = {
    var maskedMessage = message
    var maskDone : Boolean = false
    breakable {
      for ((cvvFormat, regex) <- LoggerConst.CVV_REGEX) {
        val pattern: Pattern = Pattern.compile(regex)
        val matcher: Matcher = pattern.matcher(message)
        while (matcher.find()) {
          maskedMessage = message.replaceAll(regex, cvvFormat)
          maskDone = true
        }
        if (maskDone) {
          break
        }
      }
    }
    return maskedMessage

  }

  def maskExpiryMonthBeforeLogging(message : String) : String = {
    var maskedMessage = message
    var maskDone : Boolean = false
    breakable {
      for ((expiryMonthFormat, regex) <- LoggerConst.CARD_EXPIRY_MONTH_REGEX) {
        val pattern: Pattern = Pattern.compile(regex)
        val matcher: Matcher = pattern.matcher(message)
        while (matcher.find()) {
          maskedMessage = message.replaceAll(regex, expiryMonthFormat)
          maskDone = true
        }
        if (maskDone) {
          break
        }
      }
    }
    return maskedMessage

  }

  def maskExpiryYearBeforeLogging(message : String) : String = {
    var maskedMessage = message
    var maskDone : Boolean = false
    breakable {
      for ((expiryMonthFormat, regex) <- LoggerConst.CARD_EXPIRY_YEAR_REGEX) {
        val pattern: Pattern = Pattern.compile(regex)
        val matcher: Matcher = pattern.matcher(message)
        while (matcher.find()) {
          maskedMessage = message.replaceAll(regex, expiryMonthFormat)
          maskDone = true
        }
        if (maskDone) {
          break
        }
      }
    }
    return maskedMessage

  }

  def maskEmail(message : String) : String = {
    var maskedMessage = message
    val pattern : Pattern = Pattern.compile(LoggerConst.EMAIL_REGEX)
    val regexMatcher : Matcher = pattern.matcher(message)
    while(regexMatcher.find()) {
      maskedMessage = regexMatcher.replaceAll(LoggerConst.EMAIL_REPLACE_STRING)
    }
    return maskedMessage
  }
}
