package service

import javax.inject.Inject

import consts.LoggerConst
import play.api.Logger
import play.api.libs.ws._
import utils.PayuLogger

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by nilesh.gupta on 2/21/18.
  */
class WSServiceImpl @Inject()(ws: WSClient) extends WSService{

  def networkCall(message: String): Unit = {
    PayuLogger.log("in WSServiceImpl:networkCall", LoggerConst.INFO, true)
    val request: WSRequest = ws.url("https://www.google.com")
    val complexRequest: WSRequest =
      request.withHttpHeaders("Accept" -> "application/json")
        .withQueryStringParameters("search" -> "play")
    val futureResponse: Future[WSResponse] = complexRequest.get()

    futureResponse.onComplete {
      case Success(value) =>
        Logger.logger.info("Sent back to Controller " + value.statusText)

      case Failure(e) => Logger.logger.info("Failed")
    }
  }
}
