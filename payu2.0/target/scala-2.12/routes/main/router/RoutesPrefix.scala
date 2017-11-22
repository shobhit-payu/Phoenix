
// @GENERATOR:play-routes-compiler
// @SOURCE:/Library/WebServer/Documents/scala/payu2.0/Phoenix/payu2.0/conf/routes
// @DATE:Tue Nov 21 20:24:27 IST 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
