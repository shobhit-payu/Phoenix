package actors

import actors.Hello.{`futureHi`, `hi`}
import akka.actor.{Actor, ActorLogging, Kill, PoisonPill, Props, Terminated}

/**
  * Created by vaibhav.gupta on 10/5/17.
  */
object Hello {
  def props: Props = Props[Hello]
  case object `hi`
  case object `futureHi`
}


class Hello extends Actor with ActorLogging{

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

  var initial = 1;

  def receive = {

      case `hi` =>
        println(self)
        initial +=1
        println(initial)
        try {
          var num = 1 / 1
        } catch {
          case t : Throwable => t.printStackTrace()
        }
        log.info(s"Hi, I am going to sleep")
        //Thread.sleep(5000)
        context.watch(self)

        self ! PoisonPill
        log.info(s"Hi, My sender is ${sender()}")
      case `futureHi` =>
        println("Future is called")
        //Thread.sleep(10000)
        println("Future is done")
        sender() ! "Hi from Future"
      case Terminated =>
        println("you know what, I commited suicide, don't sent me any more message")

  }
}
