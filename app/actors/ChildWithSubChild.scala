package actors

import actors.ChildWithSubChild.{`killYourOneChild`, setWatcher}
import actors.WatcherChild.oversee
import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorKilledException, ActorLogging, ActorRef, AllForOneStrategy, Kill, OneForOneStrategy, Props}

import scala.concurrent.duration._
/**
  * Created by vaibhav.gupta on 11/15/17.
  */
object ChildWithSubChild {
  def props: Props = Props[ChildWithSubChild]
  case class setWatcher(watcher: ActorRef)
  //case class Terminated(actorRef: ActorRef)
  case object `killYourOneChild`
}


class ChildWithSubChild extends Actor with ActorLogging {

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


  override val supervisorStrategy =
    AllForOneStrategy(maxNrOfRetries = 2, withinTimeRange = 1 minute) {
      case _: ActorKilledException => Restart
      case _: Exception => Escalate

    }



  val child3 = context.actorOf(Props[Child], "child3")
  val child4 = context.actorOf(Props[Child], "child4")



  def receive = {

  case `killYourOneChild` => {

    child3 ! Kill
    //child3 ! Kill
    //child3 ! Kill
    //child3 ! Kill
  }

  case setWatcher(watcher : ActorRef) => {
    watcher ! oversee(child3)
  }
    case _ => //log.info("")
  }

}
