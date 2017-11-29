package consts

import scala.collection.immutable.HashMap

/**
  * Created by hemant.agrawal on 20/11/17.
  */
object LoggerConst {

  // Custom Log Levels in Code
  val ERROR = "error"
  val INFO = "info"
  val NOTICE = "notice"
  val TRACE = "trace"
  val DEBUG = "debug"
  val WARN =  "warn"


  val CARD_REGEX : HashMap[String,String] =  HashMap(
    "Visa" -> "4[0-9]{12}(?:[0-9]{3})",
    "Mastercard" -> "5[1-5][0-9]{14}",
    "Maestro" -> "(5[06-8]|6\\d)\\d{14}(\\d{2,3})?",
    "Amex" -> "3[47][0-9]{13}",
    "Discover" -> "6(?:011|5[0-9]{2})[0-9]{12}",
    "Jcb" -> "(?:2131|1800|35\\d{3})\\d{11}",
    "Diners" -> "3(?:0[0-5]|[68][0-9])[0-9]{11}"
  )

  val CVV_REGEX :HashMap[String,String] = HashMap(
    "CVVFormat1" -> "cv:\\d{3,4}",
    "CVVFormat2" -> "vpc_CardSecurityCode:\\d{3,4}",
    "CVVFormat3" -> "cvv2:\\d{3,4}",
    "CVVFormat4" -> "cccvv:\\d{3,4}",
    "CVVFormat5" -> "ccvv:\\d{3,4}",
    "CVVFormat6" -> "cvv:\\d{3,4}",
    "CVVFormat7" -> "pgCvv2:\\d{3,4}",
    "CVVFormat8" -> "CardSecurityCode:\\d{3,4}",
    "CVVEqualsFormat1" -> "ccvv=\\d{3,4}",
    "CVVEqualsFormat2" -> "cardcvv=\\d{3,4}>",
    "CVVEqualsFormat3" -> "CV=\\d{3,4}",
    "XMLFormat" -> "<cvv2>\\d{3,4}<\\/cvv2>"
  )

  val CARD_EXPIRY_MONTH_REGEX :HashMap[String,String] = HashMap(
    "ExpiryMonthFormat1" -> "ccexpmon:\\d{1,2}",
    "ExpiryMonthFormat2" -> "<expmonth>\\d{1,2}<\\/expmonth>",
    "ExpiryMonthFormat3" -> "ccexpmon=\\d{1,2}",
    "ExpiryMonthFormat4" -> "expirymonth=\\d{1,2}"
  )

  val CARD_EXPIRY_YEAR_REGEX :HashMap[String,String] = HashMap(
    "ExpiryYearFormat1" -> "ccexpyr:\\d{4}",
    "ExpiryYearFormat2" -> "<expyear>\\d{4}<\\/expyear>",
    "ExpiryYearFormat3" -> "ccexpyr=\\d{4}",
    "ExpiryYearFormat4" -> "expiryyear=\\d{4}"
  )

  val EMAIL_REGEX = "[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"
  val EMAIL_REPLACE_STRING = "_email_"

}
