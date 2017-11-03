package actors

import javax.inject.Inject
import akka.actor._
import play.api.Logger
import akka.pattern.{after, ask}
import play.api.libs.concurrent.Akka
import play.api.Play._

import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import akka.event.Logging
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import scala.concurrent.duration._

/**
  * Created by hemant.agrawal on 29/09/17.
  */
class FirstActor(ws: WSClient) extends Actor {
  //val remote = context.actorFor("akka://HelloRemoteSystem@127.0.0.1:5150/user/RemoteActor")

  def receive = {
    case i: Int =>
      val request: WSRequest = ws.url("http://local.admin.payu.in/home")
      val complexRequest: WSRequest =
      request.withHeaders("Accept" -> "application/json")
        .withRequestTimeout(60000.millis)
      println("testing 1")
      val futureResponse: Future[WSResponse] = complexRequest.get()
      futureResponse.map(response => {
        println("responded with status " + response.status)
      })
  }

}
