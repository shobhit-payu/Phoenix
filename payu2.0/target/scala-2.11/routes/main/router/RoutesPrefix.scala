
// @GENERATOR:play-routes-compiler
// @SOURCE:/Library/WebServer/Documents/scala/payu2.0/Phoenix/payu2.0/conf/routes
// @DATE:Mon Nov 20 16:02:11 IST 2017


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
