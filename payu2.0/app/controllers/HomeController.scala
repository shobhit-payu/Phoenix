package controllers

import javax.inject._

import consts.PaymentRequest
import exception.AuthFailException
import play.api.mvc._

import utility.JsonConverter
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

  //accepts json Request
  def index = Action (parse.tolerantJson) { request =>
    val requestString = request.body.toString
    val JsonRequestObj = JsonConverter.getPaymentRequest(requestString).asInstanceOf[PaymentRequest]
    Ok("Got Payment request")
  }

}