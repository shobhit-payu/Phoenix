package actors

import actors.ChildWithSubChild._
import actors.TopLevel.{`killChild1`, `killChild3`}
import actors.WatcherChild.oversee
import akka.actor.{Actor, ActorLogging, ActorRef, OneForOneStrategy, PoisonPill, Props}
import akka.actor.SupervisorStrategy._

import scala.concurrent.duration._

/**
  * Created by vaibhav.gupta on 11/15/17.
  */

object TopLevel {
  def props: Props = Props[WatcherChild]
  case object `killChild1`
  case object `killChild3`
}

class TopLevel extends Actor with ActorLogging{

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
    OneForOneStrategy(maxNrOfRetries = 3, withinTimeRange = 1 minute) {
      case _: Exception => Restart
    }

  val child1 = context.actorOf(Props[Child], "child1")
  val WatcherChild = context.actorOf(Props[WatcherChild], "WatcherChild")
  WatcherChild ! oversee(child1)
  val childWithSubChild = context.actorOf(Props[ChildWithSubChild], "childWithSubChild")
  childWithSubChild ! setWatcher(WatcherChild)



  def receive = {

    case `killChild1` => {

      //child1 ! Stop     //Watching actor won't be triggered due to graceful Stop
      child1 ! PoisonPill
      child1 ! "hi"

    }

    case `killChild3` => {

      //child1 ! Stop     //Watching actor won't be triggered due to graceful Stop
      childWithSubChild ! killYourOneChild

    }
    case _ => {
      log.info("No behaviour is defined in TopLevel")
    }
  }

}
