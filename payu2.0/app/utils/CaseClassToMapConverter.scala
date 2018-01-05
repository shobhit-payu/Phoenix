package utils

/**
  * Created by pragya.mishra on 1/3/18.
  */
object CaseClassToMapConverter {

  def getMap(cc: AnyRef) = {
    val resMap: Map[String, Any] = Map[String, Any]()
    for (f <- cc.getClass.getDeclaredFields) {
      f.setAccessible(true)
      resMap + (f.getName -> f.get(cc))
    }
    resMap
  }

}
