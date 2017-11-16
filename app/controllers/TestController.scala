package controllers

import java.io.File

import play.api.mvc.{AbstractController, Action, ActionBuilder, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future, Promise}
import javax.inject._

import actors.{Hello, TopLevel}
import actors.Hello._
import actors.TopLevel._
import akka.actor.Status.{Failure, Success}
import akka.actor.{ActorRef, ActorSelection, ActorSystem, Props}

@Singleton
class TestController @Inject()(cc: ControllerComponents) extends AbstractController(cc){

  val system: ActorSystem = ActorSystem("superMonitor")

  val topLevel : ActorRef = system.actorOf(Props[TopLevel], "topLevel")

  val local : ActorRef = system.actorOf(Props[Hello], "local")

    def normal = Action {
      //local ! hi
      Ok("Application is started now")
    }

    def killChild = Action {
      topLevel ! killChild1
      Ok("Killing child1")
    }

    def killChildOfChild = Action {
      topLevel ! killChild3
      Ok("Killing child3")
    }

}
