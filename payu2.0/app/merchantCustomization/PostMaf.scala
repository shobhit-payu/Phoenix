package merchantCustomization

import model.PaymentRequest

/**
  * Created by pragya.mishra on 12/1/17.
  */
object PostMaf {

  def siEnabled (paymentRequest : PaymentRequest) : Unit = {
    println("si after maf")
    paymentRequest.key = "s2s_enabled"
  }

  def s2sEnabled (paymentRequest : PaymentRequest) : Unit = {
    println("s2s after maf" + paymentRequest.txnid)
  }

}
