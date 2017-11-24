package utility

import scala.collection.mutable
import scala.collection.mutable._

/**
  * Created by pragya.mishra on 11/24/17.
  */

object CaseClassToMapConverter {
  def ccToMap(cc: AnyRef) : (mutable.Map[String, String])= {
    var returnMap = mutable.Map[String, String]()
    val fieldArray = cc.getClass.getDeclaredFields
    for ( f <- fieldArray) {
      f.setAccessible(true)
      var v  = f.getName -> f.get(cc)
      returnMap = returnMap + v.asInstanceOf[(String, String)]
    }
    return  returnMap
  }
}
