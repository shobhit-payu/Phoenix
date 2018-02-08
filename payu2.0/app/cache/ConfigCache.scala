package cache

import consts.CacheType
import repository.ConfigRepository

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

/**
  * Created by pragya.mishra on 2/8/18.
  */

object ConfigCache {

  private val cacheInstance : GuavaCache[String] = GuavaCache(CacheType.CONFIG)

  def doGet(key: String): String = {
    val valueFromCache = cacheInstance.doGet(key)
    if (valueFromCache != null) {
      valueFromCache.toString
    } else {
      val valueFromDb = getValueForKey(key)
      cacheInstance.doPut(key,valueFromDb)
      valueFromDb
    }
  }

  private def getValueForKey (key : String) : String = {
    val f : Future[Seq[String]] = ConfigRepository.getValueForKey(key)
    val res = Await.result(f,1000.millis)
    res.mkString("")
  }

  def doPut(key: String, value: String): Any = cacheInstance.doPut(key, value)

  def doRemove(key: String) = cacheInstance.doRemove(key)

  def doRemoveAll = cacheInstance.doRemoveAll

}
