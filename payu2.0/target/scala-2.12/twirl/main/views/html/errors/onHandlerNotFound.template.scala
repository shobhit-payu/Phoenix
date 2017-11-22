
package views.html.Errors

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object onHandlerNotFound extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.26*/("""

"""),_display_(/*3.2*/main("Handler Not Found")/*3.27*/ {_display_(Seq[Any](format.raw/*3.29*/("""

"""),format.raw/*5.1*/("""<h1>Handler Not Found</h1>
<p>You requested: """),_display_(/*6.20*/request/*6.27*/.path),format.raw/*6.32*/("""</p>

""")))}))
      }
    }
  }

  def render(request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(request)

  def f:((RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (request) => apply(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Nov 21 20:27:12 IST 2017
                  SOURCE: /Library/WebServer/Documents/scala/payu2.0/Phoenix/payu2.0/app/views/Errors/onHandlerNotFound.scala.html
                  HASH: b52df2ecce230052359be1ce05f0748f988a1035
                  MATRIX: 755->1|874->25|902->28|935->53|974->55|1002->57|1074->103|1089->110|1114->115
                  LINES: 21->1|26->1|28->3|28->3|28->3|30->5|31->6|31->6|31->6
                  -- GENERATED --
              */
          