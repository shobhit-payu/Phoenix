package controllers

import java.io.File

import actors._
import javax.inject._

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import play.api.mvc._
import play.api.libs.ws._
import play.api.http.HttpEntity
import akka.actor.{Actor, ActorSystem, Props}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.{ByteString, Timeout}
import akka.pattern.ask
import com.typesafe.config.ConfigFactory
import play.api.libs.concurrent.Akka
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.duration._
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global



class HomeController @Inject() (ws: WSClient) extends Controller {

  def index = Action {

    val configFile = getClass.getClassLoader.getResource("ContextConf.conf").getFile
    val config = ConfigFactory.parseFile(new File(configFile))

    val system: ActorSystem = ActorSystem("mainSystem", config)

    val ecActor = system.actorOf(Props (new ExecutionContextExampleActor))
    val wsActor = system.actorOf(Props (new FirstActor(ws)))
    for (i <- 1 to 10) {
      ecActor ! i
      wsActor ! i
    }
    Ok("Done!!")
  }


}
