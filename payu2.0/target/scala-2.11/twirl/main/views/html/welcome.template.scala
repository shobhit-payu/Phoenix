
package views.html

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

object welcome extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String, style: String = "scala"):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.44*/("""

"""),_display_(/*3.2*/defining(play.core.PlayVersion.current)/*3.41*/ { version =>_display_(Seq[Any](format.raw/*3.54*/("""

"""),format.raw/*5.1*/("""<section id="top">
    <div class="wrapper">
        <h1><a href="https://playframework.com/documentation/"""),_display_(/*7.63*/version),format.raw/*7.70*/("""/Home">"""),_display_(/*7.78*/message),format.raw/*7.85*/("""</a></h1>
    </div>
</section>

<div id="content" class="wrapper doc">
    <article>

        <h1>Welcome to Play</h1>

        <p>
            Congratulations, you’ve just created a new Play application. This page will help you with the next few steps.
        </p>

        <blockquote>
            <p>
                You’re using Play """),_display_(/*22.36*/version),format.raw/*22.43*/("""
            """),format.raw/*23.13*/("""</p>
        </blockquote>

        <h2>Why do you see this page?</h2>

        <p>
            The <code>conf/routes</code> file defines a route that tells Play to invoke the <code>HomeController.index</code> action
            whenever a browser requests the <code>/</code> URI using the GET method:
        </p>

        <pre><code># Home page
GET     /               controllers.HomeController.index</code></pre>

        <p>
            Play has invoked the <code>controllers.HomeController.index</code> method to obtain the <code>Action</code> to execute:
        </p>

        <pre><code>def index = Action """),format.raw/*40.39*/("""{"""),format.raw/*40.40*/(""" """),format.raw/*40.41*/("""implicit request: Request[AnyContent] =>
  Ok(views.html.index("Your new application is ready!"))
"""),format.raw/*42.1*/("""}"""),format.raw/*42.2*/("""</code></pre>

        <p>
            An action is a function that handles the incoming HTTP request, and returns the HTTP result to send back to the web client.
            Here we send a <code>200 OK</code> response, using a template to fill its content.
        </p>

        <p>
            The template is defined in the <code>app/views/index.scala.html</code> file and compiled as a Scala function.
        </p>

        <pre><code>@(message: String)

@main("Welcome to Play") """),format.raw/*55.27*/("""{"""),format.raw/*55.28*/("""

    """),format.raw/*57.5*/("""@welcome(message)

"""),format.raw/*59.1*/("""}"""),format.raw/*59.2*/("""</code></pre>

        <p>
            The first line of the template defines the function signature. Here it just takes a single <code>String</code> parameter.
            This template then calls another function defined in <code>app/views/main.scala.html</code>, which displays the HTML
            layout, and another function that displays this welcome message. You can freely add any HTML fragment mixed with Scala
            code in this file.
        </p>

        <p>You can read more about <a href="https://www.playframework.com/documentation/"""),_display_(/*68.90*/version),format.raw/*68.97*/("""/ScalaTemplates">Twirl</a>, the template language used by Play, and how Play handles <a href="https://www.playframework.com/documentation/"""),_display_(/*68.236*/version),format.raw/*68.243*/("""/ScalaActions">actions</a>.</p>

        <h2>Async Controller</h2>

        Now that you've seen how Play renders a page, take a look at <code>AsyncController.scala</code>, which shows how to do asynchronous programming when handling a request.  The code is almost exactly the same as <code>HomeController.scala</code>, but instead of returning <code>Result</code>, the action returns <code>Future[Result]</code> to Play.  When the execution completes, Play can use a thread to render the result without blocking the thread in the mean time.


        <p>
            You can read more about <a href="https://www.playframework.com/documentation/"""),_display_(/*76.91*/version),format.raw/*76.98*/("""/ScalaDependencyInjection">dependency injection</a> in the documentation.
        </p>

        <h2>Need more info on the console?</h2>

        <p>
            For more information on the various commands you can run on Play, i.e. running tests and packaging applications for production, see <a href="https://playframework.com/documentation/"""),_display_(/*82.195*/version),format.raw/*82.202*/("""/PlayConsole">Using the Play console</a>.
        </p>

        <h2>Need to set up an IDE?</h2>

        <p>
            You can start hacking your application right now using any text editor. Any changes will be automatically reloaded at each page refresh,
            including modifications made to Scala source files.
        </p>

        <p>
            If you want to set-up your application in <strong>IntelliJ IDEA</strong> or any other Java IDE, check the
            <a href="https://www.playframework.com/documentation/"""),_display_(/*94.67*/version),format.raw/*94.74*/("""/IDE">Setting up your preferred IDE</a> page.
        </p>

        <h2>Need more documentation?</h2>

        <p>
            Play documentation is available at <a href="https://www.playframework.com/documentation/"""),_display_(/*100.102*/version),format.raw/*100.109*/("""">https://www.playframework.com/documentation</a>.
        </p>

        <p>
            Play comes with lots of example templates showcasing various bits of Play functionality at <a href="https://www.playframework.com/download#examples">https://www.playframework.com/download#examples</a>.
        </p>

        <h2>Need more help?</h2>

        <p>
            Play questions are asked and answered on Stackoverflow using the "playframework" tag: <a href="https://stackoverflow.com/questions/tagged/playframework">https://stackoverflow.com/questions/tagged/playframework</a>
        </p>

        <p>
            The <a href="http://groups.google.com/group/play-framework">Play Google Group</a> is where Play users come to seek help,
            announce projects, and discuss issues and new features. If you don’t have a Google account, you can still join the mailing
            list by sending an e-mail to
            <strong>play-framework+subscribe@googlegroups.com</strong>.
        </p>

        <p>
            Gitter is a real time chat channel, like IRC. The <a href="https://gitter.im/playframework/playframework">playframework/playframework</a>  channel is used by Play users to discuss the ins and outs of writing great Play applications.
        </p>

    </article>

    <aside>
        <h3>Browse</h3>
        <ul>
            <li><a href="https://playframework.com/documentation/"""),_display_(/*129.67*/version),format.raw/*129.74*/("""">Documentation</a></li>
            <li><a href="https://playframework.com/documentation/"""),_display_(/*130.67*/version),format.raw/*130.74*/("""/api/"""),_display_(/*130.80*/style),format.raw/*130.85*/("""/index.html">Browse the """),_display_(/*130.110*/{style.capitalize}),format.raw/*130.128*/(""" """),format.raw/*130.129*/("""API</a></li>
        </ul>
        <h3>Start here</h3>
        <ul>
            <li><a href="https://playframework.com/documentation/"""),_display_(/*134.67*/version),format.raw/*134.74*/("""/PlayConsole">Using the Play console</a></li>
            <li><a href="https://playframework.com/documentation/"""),_display_(/*135.67*/version),format.raw/*135.74*/("""/IDE">Setting up your preferred IDE</a></li>
            <li><a href="https://playframework.com/download#examples">Example Projects</a>
        </ul>
        <h3>Help here</h3>
        <ul>
            <li><a href="https://stackoverflow.com/questions/tagged/playframework">Stack Overflow</a></li>
            <li><a href="http://groups.google.com/group/play-framework">Mailing List</a></li>
            <li><a href="https://gitter.im/playframework/playframework">Gitter Channel</a></li>
        </ul>

    </aside>

</div>
""")))}),format.raw/*148.2*/("""
"""))
      }
    }
  }

  def render(message:String,style:String): play.twirl.api.HtmlFormat.Appendable = apply(message,style)

  def f:((String,String) => play.twirl.api.HtmlFormat.Appendable) = (message,style) => apply(message,style)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Nov 20 15:58:54 IST 2017
                  SOURCE: /Library/WebServer/Documents/scala/payu2.0/Phoenix/payu2.0/app/views/welcome.scala.html
                  HASH: 2265d3da70875578f33d48163d8d6cd85a5452a3
                  MATRIX: 738->1|875->43|903->46|950->85|1000->98|1028->100|1161->207|1188->214|1222->222|1249->229|1617->570|1645->577|1686->590|2328->1204|2357->1205|2386->1206|2511->1304|2539->1305|3051->1791|3080->1792|3113->1798|3159->1818|3187->1819|3769->2374|3797->2381|3964->2520|3993->2527|4666->3173|4694->3180|5065->3523|5094->3530|5653->4062|5681->4069|5926->4285|5956->4292|7384->5693|7413->5700|7532->5791|7561->5798|7595->5804|7622->5809|7676->5834|7717->5852|7748->5853|7910->5987|7939->5994|8079->6106|8108->6113|8663->6637
                  LINES: 21->1|26->1|28->3|28->3|28->3|30->5|32->7|32->7|32->7|32->7|47->22|47->22|48->23|65->40|65->40|65->40|67->42|67->42|80->55|80->55|82->57|84->59|84->59|93->68|93->68|93->68|93->68|101->76|101->76|107->82|107->82|119->94|119->94|125->100|125->100|154->129|154->129|155->130|155->130|155->130|155->130|155->130|155->130|155->130|159->134|159->134|160->135|160->135|173->148
                  -- GENERATED --
              */
          