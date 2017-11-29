package exception

import play.api.Logger

case class AuthFailException(errorCode : Int, message: String) extends AppException {

}
