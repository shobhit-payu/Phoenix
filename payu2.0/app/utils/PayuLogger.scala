package utils

import consts._
import play.api.Logger

/**
  * Created by hemant.agrawal on 22/11/17.
  */
object PayuLogger{

  def log (message: String, logLevel : String = LoggerConst.INFO, mask : Boolean = false) = {

    var logMessage : String = message

    // do mask the message before logging
    if (mask) {
      logMessage = LoggerMaskingUtil.doAllMaskingBeforeLogging(message)
    }

    logLevel match {
      case LoggerConst.ERROR => {
        Logger.error(logMessage)
      }
      case LoggerConst.INFO => {
        Logger.info(logMessage)
      }
      case LoggerConst.DEBUG => {
        Logger.debug(logMessage)
      }
      case LoggerConst.WARN => {
        Logger.warn(logMessage)
      }
      case LoggerConst.TRACE => {
        Logger.trace(logMessage)
      }
      case _ =>{
        Logger.error(logMessage)
      }
    }
  }

}
