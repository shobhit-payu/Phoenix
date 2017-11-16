package actors

import akka.actor.{Actor, ActorLogging}

/**
  * Created by vaibhav.gupta on 11/15/17.
  */
class Child extends Actor with ActorLogging {

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
    case _ => //log.info("")
  }

}
