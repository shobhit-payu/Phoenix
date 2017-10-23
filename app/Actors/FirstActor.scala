package Actors

import akka.actor.Actor
import javax.inject.Inject
import play.api.Logger
import play.api.libs.ws._
import scala.concurrent.duration._
import scala.util.{Success, Failure}
import play.api.libs.ws.{WSClient, WSResponse}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by pragya.mishra on 9/20/17.
  */

case object printHello
case class printMessage(s: String)
case class doNetworkCall(ws:WSClient)

class FirstActor @Inject extends Actor {
  def receive = {

    case `printHello` => {
      println("Hello from FirstActor")
    }

    case printMessage(s: String) => {
      println(s)
    }

    case doNetworkCall(ws : WSClient) => {
      val s = sender()
      val request: WSRequest = ws.url("https://www.google.com")
      val complexRequest: WSRequest =
        request.withHeaders("Accept" -> "application/json")
          .withRequestTimeout(10000.millis)
          .withQueryString("search" -> "play")
      val futureResponse: Future[WSResponse] = complexRequest.get()

      futureResponse.onComplete {
        case Success(value) =>
                              Logger.logger.info("Sent back to Controller "+ value.statusText)
                              s ! value.statusText
        case Failure(e) =>    Logger.logger.info("Failed")
      }
    }

    case _ => {
      println("Default case")
    }

  }

}

