package exception

import javax.inject.Singleton

import play.api.Logger
import play.api.http.HttpErrorHandler
import play.api.libs.json.Json
import play.api.mvc.Results._
import play.api.mvc._

import scala.concurrent._;

@Singleton
class PayuErrorHandler extends HttpErrorHandler {

  def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
    statusCode match {
      case play.api.http.Status.NOT_FOUND => {
        Future.successful(
          NotFound(views.html.Errors.onHandlerNotFound(request))
        )
      }
      case play.api.http.Status.BAD_REQUEST => {
        Future.successful(
          NotFound(views.html.Errors.onHandlerNotFound(request))
        )
      }
      case play.api.http.Status.BAD_GATEWAY => {
        Future.successful(
          NotFound(views.html.Errors.onHandlerNotFound(request))
        )
      }
      case _ => {
        Future.successful(
          Status(statusCode)("Unknown response")
        )
      }
    }

  }

  def onServerError(request: RequestHeader, exception: Throwable) = {
    exception match {
      case exception: AppException => {     // known custom exception
        Logger.error("Payu exception occurred, ErrorCode: " + exception.errorCode + " Message : " + exception.message)
        exception.printStackTrace()
        Future.successful(
          InternalServerError(
            Json.toJson(Json.obj(
              "message" -> exception.message,
              "errorCode" -> exception.errorCode
            ))
          )
        )
      }
      case _ => {
        Logger.error("Exception occurred "  + exception.getMessage())
        exception.printStackTrace()
        Future.successful(
          InternalServerError(views.html.Errors.onError(exception))
        )
      }
    }
  }

}