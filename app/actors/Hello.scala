package actors

import actors.Hello.{`futureHi`, `hi`}
import akka.actor.{Actor, ActorLogging, Props}

/**
  * Created by vaibhav.gupta on 10/5/17.
  */
object Hello {
  def props: Props = Props[Hello]
  case object `hi`
  case object `futureHi`
}


class Hello extends Actor with ActorLogging{

  def receive = {
      case `hi` =>
        log.info(s"Hi, I am going to sleep")
        //Thread.sleep(5000)
        log.info(s"Hi, My sender is ${sender()}")
      case `futureHi` =>
        println("Future is called")
        //Thread.sleep(10000)
        println("Future is done")
        sender() ! "Hi from Future"
  }
}
