package actors

import actors.WatcherChild.oversee
import akka.actor.{Actor, ActorLogging, ActorRef, Props, Terminated}
//import akka.remote.ContainerFormats.ActorRef

/**
  * Created by vaibhav.gupta on 11/16/17.
  */

object WatcherChild {
  def props: Props = Props[WatcherChild]
  case class oversee(actorRef: ActorRef)
  //case class Terminated(actorRef: ActorRef)
  case object `futureHi`
}

class WatcherChild  extends Actor with ActorLogging {

  override def preStart(): Unit ={
    println("Starting " + self)
  }

  override def postStop():Unit = {
    println("Stopped " + self)
  }

  override def postRestart(reason: Throwable): Unit = {
    println("Restarted " + self + " because of " + reason)
    //preStart()
  }

    def receive = {

    case oversee(actorRef) => {
      println(s"watching $actorRef")
      context.watch(actorRef)
    }

    case Terminated(actorRef) => {
      println(s"I was watching $actorRef, someone killed it")
    }

    case _ =>
  }

}
