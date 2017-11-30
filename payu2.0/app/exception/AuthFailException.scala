package exception

case class AuthFailException(errorCode : Int, message: String) extends AppException {

}
