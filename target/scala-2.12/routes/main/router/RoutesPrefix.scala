
// @GENERATOR:play-routes-compiler
// @SOURCE:/private/var/www/Phoenix/conf/routes
// @DATE:Fri Oct 27 11:40:56 IST 2017


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
