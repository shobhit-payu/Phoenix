package utils

import merchantCustomization.PostValidation
import model.PaymentRequest
import consts.FlagToStrategyMappingConst

/**
  * Created by pragya.mishra on 12/1/17.
  */

object ImplementStrategy {

  def execute(f:(PaymentRequest) => Unit, paymentRequest: PaymentRequest) = f(paymentRequest)
  //def executeWithString(callback:(PaymentRequest, String) => Unit, paymentRequest: PaymentRequest, str : String) = callback(paymentRequest, str)

  def executeAfterValidation(paymentRequest: PaymentRequest, merchantParams : List[String]): Unit = {
    for (param <- merchantParams) execute(FlagToStrategyMappingConst.AFTER_VALIDATION(param), paymentRequest)
  }

  def executeAfterMaf(paymentRequest: PaymentRequest, merchantParams : List[String]): Unit = {
    for (param <- merchantParams) execute(FlagToStrategyMappingConst.AFTER_MAF(param), paymentRequest)
  }

}
