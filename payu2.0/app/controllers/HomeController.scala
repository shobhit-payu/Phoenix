package controllers

import javax.inject._
import play.api.mvc._

import consts.LoggerConst
import exception.AuthFailException
import service.StudentService
import utils.PayuLogger

import model.Student

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
  def index = Action {
    PayuLogger.log("cv:123 4386280006271757 hemant@gmail.com ",LoggerConst.INFO,true)
    throw new AuthFailException(1,"2")
    //AuthFailException.

    //throw AuthFailException
    Ok("Done!!")
  }

  def listAll = Action {

    //read all table data
    val s1 = StudentService.listAllStudents
    s1.onSuccess { case s => println(s"select all rows: $s") }

    //select only the record with id = 2
    val s2 = StudentService.findById(2l)
    s2.onSuccess { case s => println(s"select all columns: $s") }

    //select only name with rollNo = 99
    val s3 = StudentService.findNameByRollNo(10)
    s3.onSuccess { case s => println(s"select selected column: $s") }

    //insert new record
    val student = Student(0l, "Blake", 10, 19)
    val s4 = StudentService.addNewStudent(student)
    //println(s"Result4: $s4")
    s4.onSuccess { case s => println(s"insert row: $s") }

    //update roll no of a record
    val s5 = StudentService.updateRollNoAndNameById(8l, "Ben", 41)
    s5.onSuccess { case s => println(s"Update Result: $s") }

    //delete a record
    val s6 = StudentService.deleteByRollNo(99)
    s6.onSuccess { case s => println(s"Delete Result: $s") }

    //joins - select
    val s7 = StudentService.selectOnId
    s7.onSuccess { case s => println(s"Join Result: $s") }

    //native sql select query
    val s8 = StudentService.nativeSelect(2l)
    s8.onSuccess { case s => println(s"Native Select Result: $s") }

    //native sql update query (dml)
    val s9 = StudentService.nativeUpdate(1l, "Cathy")
    s9.onSuccess { case s => println(s"Native Update Result: $s") }

    Ok("done")
  }

}