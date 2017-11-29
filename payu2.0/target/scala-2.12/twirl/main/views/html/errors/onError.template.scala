
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

object onError extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Throwable,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(throwable: Throwable):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.24*/("""

"""),_display_(/*3.2*/main("onError Happened")/*3.26*/ {_display_(Seq[Any](format.raw/*3.28*/("""

"""),format.raw/*5.1*/("""<h1>onError Happened</h1>
<p>"""),_display_(/*6.5*/throwable/*6.14*/.getMessage),format.raw/*6.25*/("""</p>

""")))}),format.raw/*8.2*/("""
"""))
      }
    }
  }

  def render(throwable:Throwable): play.twirl.api.HtmlFormat.Appendable = apply(throwable)

  def f:((Throwable) => play.twirl.api.HtmlFormat.Appendable) = (throwable) => apply(throwable)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Nov 21 20:27:12 IST 2017
                  SOURCE: /Library/WebServer/Documents/scala/payu2.0/Phoenix/payu2.0/app/views/Errors/onError.scala.html
                  HASH: 8a256d26afba4f2a88692a20872b4732c5d7bc0b
                  MATRIX: 741->1|858->23|886->26|918->50|957->52|985->54|1040->84|1057->93|1088->104|1124->111
                  LINES: 21->1|26->1|28->3|28->3|28->3|30->5|31->6|31->6|31->6|33->8
                  -- GENERATED --
              */
          