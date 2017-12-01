package merchantCustomization

import model.PaymentRequest

/**
  * Created by pragya.mishra on 12/1/17.
  */
object PostValidation {

  def siEnabled (paymentRequest : PaymentRequest) : Unit = {
    println("si after validation")
  }

  def s2sEnabled (paymentRequest : PaymentRequest) : Unit = {
    println("s2s after validation")
    paymentRequest.txnid = "123"
  }

}
