package consts

import merchantCustomization.{PostValidation, PostMaf}

import scala.collection.immutable.HashMap

import model.PaymentRequest
import  scala.Nothing

/**
  * Created by pragya.mishra on 12/1/17.
  */

object FlagToStrategyMappingConst {

  // Maintain the flags in DB , say merchantFlags & AFTER_VALIDATION flags
  // Take join and then use this

  val AFTER_VALIDATION : HashMap[String,(PaymentRequest) => Unit] =  HashMap(
    "si_enabled" -> PostValidation.siEnabled,
    "s2s_enabled" -> PostValidation.s2sEnabled
  )

  val AFTER_MAF : HashMap[String,(PaymentRequest) => Unit] =  HashMap(
    "si_enabled" -> PostMaf.siEnabled,
    "s2s_enabled" -> PostMaf.s2sEnabled
  )

}
