
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/pragya.mishra/Desktop/Commit/conf/routes
// @DATE:Mon Oct 23 17:27:58 IST 2017


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
