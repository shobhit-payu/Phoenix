package controllers

import java.io.File

import play.api.mvc.{AbstractController, Action, ActionBuilder, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future, Promise}
import javax.inject._

import actors.Hello
import actors.Hello._
import akka.actor.Status.{Failure, Success}
import akka.actor.{ActorRef, ActorSelection, ActorSystem, Props}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory
import akka.pattern.ask
import akka.util.Timeout
import play.api.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

@Singleton
class TestController @Inject()(cc: ControllerComponents) extends AbstractController(cc){

  val configFile = getClass.getClassLoader.getResource("myConfig.conf").getFile
  val config = ConfigFactory.parseFile(new File(configFile))

  val system: ActorSystem = ActorSystem("mainSystem", config)

  val hello : ActorRef = system.actorOf(Props[Hello])
  println(s"normal Actor reference is $hello")

  val router1: ActorRef = system.actorOf(FromConfig.props(Props[Hello]).withDispatcher("my-dispatcher"), "router1")
  println(s"Router1 reference is $router1")

  //val router2: ActorRef = system.actorOf(FromConfig.props(Props[Hello]), "router2")

  //remote router
  val router2: ActorSelection = system.actorSelection("akka.tcp://remoteSystem@127.0.0.1:9003/user/remoteRouter")
  println(s"Router2 reference is $router2")

  def normal = Action.async {
    //hello ! hi

    implicit val timeout = Timeout(5 seconds)

    val f = (hello ? futureHi)
    val r = scala.util.Random
    val x = r.nextInt
    f.map(msg => {
      Ok(s"Things are working $f")
    })
    /*
    f.onComplete  {
      case Success(value) => Ok("Normal is working now")
      case Failure(e) => Ok("Normal is not working")
    }
    */
  }

//  def localrouter = Action {
//    router1 ! hi
//    Ok("Local router is working now")
//  }

  def localrouter = Action.async {
    //hello ! hi

    implicit val timeout = Timeout(5 seconds)

    val f = (router1 ? futureHi)
    val r = scala.util.Random
    val x = r.nextInt
    f.map(msg => {
      //println(s"$f")
      Ok(s"Things are working $f")

    })
  }


  def remoterouter = Action.async {

    implicit val timeout = Timeout(5 seconds)

    val f = (router2 ? futureHi)
    val r = scala.util.Random
    val x = r.nextInt
    f.map(msg => {
      Ok(s"Things are working $f")
    })

  }

}
