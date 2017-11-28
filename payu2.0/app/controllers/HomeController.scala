package controllers

import javax.inject._

import exception.AuthFailException
import play.api.mvc._

import validators.PaymentRequestValidation
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def exception = Action {
    throw AuthFailException
    Ok("Test Page")
  }

  /**
    * End point that accepts POST json (payment request) from merchant
    * @return OK
    */
  def index = Action (parse.tolerantJson) { request =>
    val requestString = request.body.toString
    PaymentRequestValidation.doValidation(requestString)
    Ok("Got Payment request")
  }

}