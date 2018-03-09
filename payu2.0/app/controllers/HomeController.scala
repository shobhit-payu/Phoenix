package controllers

import javax.inject._

import consts.LoggerConst
import exception.AuthFailException
import play.api.mvc._
import utils.PayuLogger
import validators.PaymentRequestValidation

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents)(paymentRequestValidation:PaymentRequestValidation) extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def exception = Action {
    PayuLogger.log("cv:123 4386280006271757 hemant@gmail.com ", LoggerConst.INFO, true)
    throw new AuthFailException("1", "2")

    //throw AuthFail Exception
    Ok("Test Page")
  }

  /**
    * End point that accepts POST json (payment request) from merchant
    *
    * @return OK
    */
  def index = Action(parse.tolerantJson) { request =>
    val requestString = request.body.toString
    paymentRequestValidation.doValidation(requestString)
    Ok("Got Payment request")
  }

}