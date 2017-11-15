package controllers

import java.io.File

import play.api.mvc.{AbstractController, Action, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future, Promise}
import javax.inject._

import actors.Hello
import actors.Hello._
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._

@Singleton
class TestController @Inject()(cc: ControllerComponents) extends AbstractController(cc){

  val configFile = getClass.getClassLoader.getResource("myConfig.conf").getFile
  val config = ConfigFactory.parseFile(new File(configFile))

  val system: ActorSystem = ActorSystem("remoteSystem", config)

  //val hello : ActorRef = system.actorOf(Props[Hello])
  //println(s"normal Actor reference is $hello")
  //val router1: ActorRef = system.actorOf(FromConfig.props(Props[Hello]), "router1")
  //println(s"Router1 reference is $router1")

  //reading configuration from application.conf

  val remoteRouter: ActorRef = system.actorOf(FromConfig.props(Props[Hello]).withDispatcher("my-dispatcher"), "remoteRouter")
  println(s"remoteRouter reference is $remoteRouter")

  /*def normal = Action {
    hello ! hi
    Ok("Normal is working now")
  }

  def localrouter = Action {
    router1 ! hi
    Ok("Local router is working now")
  }
*/
  def remoterouter = Action {
    remoteRouter ! hi
    Ok("Remote router is working now")
  }

}

