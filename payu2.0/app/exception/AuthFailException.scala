package exception

import play.api.Logger

object AuthFailException extends AppException {
  val errorCode = 2
  val message = "test exception"

  Logger.error("Exception occurred, ErrorCode: " + errorCode + " Message : " + message)
}
