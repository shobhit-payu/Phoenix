package service

import model.Config
import repository.ConfigRepository
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Created by pragya.mishra on 1/3/18.
  */
object ConfigService {

  def getValueForKey (key : String) : String = {
    val f : Future[Seq[String]] = ConfigRepository.getValueForKey(key)
    val res = Await.result(f,1000.millis)
    res.mkString("")
  }
}
