package controllers

import javax.inject._

import Actors.{printHello, printMessage, FirstActor, doNetworkCall}
import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import play.api.mvc._
import play.api.libs.ws._
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.duration._
import akka.pattern.ask



/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject() (system : ActorSystem) (ws : WSClient)  extends Controller {
  val firstActor = system.actorOf(Props[FirstActor], name = "actor")
  implicit val timeout = Timeout(10 seconds)

  def index = Action.async {

    firstActor ! printHello

    firstActor ! printMessage("Playing with Scala")

    firstActor ! "What's up"

    val futureResponse = firstActor ? doNetworkCall(ws)

    futureResponse.map(response => {
      Logger.logger.info("Responded with status " + response)
      Ok("Success !!")
    })

  }
}