package actors


import CustomConfig.AppConfig
import akka.actor._
import akka.pattern.{after, ask}
import play.api.libs.concurrent.Akka
import play.api.Play._
import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global


/**
  * Created by hemant.agrawal on 29/09/17.
  */
class ExecutionContextExampleActor extends Actor{


  def receive = {
    case i: Int => {
      println(s"Calling blocking Future: ${i}")
      Future {
        Thread.sleep(5000) //block for 5 seconds
        println(s"Blocking future finished ${i}")
      }
    }

  }

}
