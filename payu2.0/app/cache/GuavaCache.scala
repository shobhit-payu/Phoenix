package cache

import java.util.concurrent.TimeUnit

import com.google.common.cache.{Cache => GCache, CacheBuilder => GCacheBuilder}
import com.typesafe.config.ConfigFactory
import consts.CacheValueEntry

/**
  * Created by pragya.mishra on 2/7/18.
  */

class GuavaCache[V](underlying: GCache[String,CacheValueEntry[V]]) {

  def doGet(key: String) = {
    if (underlying.getIfPresent(key) != null) {
      underlying.getIfPresent(key).value
    } else null
  }

  def doPut(key: String, value: V): Any = {
    underlying.put(key, CacheValueEntry(value))
  }

  def doRemove(key: String) = {
    underlying.invalidate(key)
  }

  def doRemoveAll = {
    underlying.invalidateAll()
  }

}

object GuavaCache {
  /**
    * Create a new Guava cache
    */
  def apply[V](cacheType : String): GuavaCache[V] = {
    val expiry = (ConfigFactory.load().getString("cache." + cacheType + ".expiry")).toLong
    apply(GCacheBuilder.newBuilder().expireAfterWrite(expiry, TimeUnit.MINUTES).build[String,CacheValueEntry[V]]())
  }

  /**
    * Create a new cache utilizing the given underlying Guava cache.
    *
    * @param underlying a Guava cache
    */
  def apply[V](underlying: GCache[String,CacheValueEntry[V]]) : GuavaCache[V] =
    new GuavaCache(underlying)

}