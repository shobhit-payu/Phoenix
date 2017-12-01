package exception

import play.api.Logger


case class AuthFailException(errorCode : String, message: String) extends AppException {
}

case class PaymentFlowException (errorCode : String, message: String) extends AppException {
}