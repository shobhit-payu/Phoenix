package repository

import db.DBComponent
import model.Student
import model.StudentInfo
import slick.jdbc.MySQLProfile.api._



object StudentRepository extends DBComponent
{

  private [repository] val students = TableQuery[StudentTable]
  private [repository] val studentInfo = TableQuery[StudentInfoTable]



  def listAllStudents = {
    db.run(students.result)
  }

  def findById(id : Long) = {
    db.run(students.filter(_.id === id).result)
  }

  def addNewStudent(student: Student) = {
    db.run(students += student)
  }

  def findNameByRollNo(rollNo : Int) = {
    db.run(students.filter(_.rollNo === rollNo).map(_.name).result)
  }

  def updateRollNoAndNameById(id : Long, name : String, rollNo : Int) = {
    db.run(students.filter(_.id === id).map(s => (s.name , s.rollNo)).update(name, rollNo))
  }

  def deleteByRollNo(rollNo : Int) = {
    db.run(students.filter(_.rollNo === rollNo).delete)
  }

  def selectOnId = {
    db.run(students.join(studentInfo).on(_.id === _.id).map { case (s, si) => (s.name, si.subject)}.result)
  }

  def nativeSelect(id : Long) = {
    db.run(sql"""select name from student where id = $id""".as[(String)]
    )
  }

  def nativeUpdate(id : Long, name : String) = {
    db.run(sqlu"""update student set name = $name where id = $id"""
    )
  }


  private[repository] class StudentTable(tag: Tag) extends Table[Student](tag, "STUDENT") {

    def id = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    def name = column[String]("NAME")
    def grade = column[Int]("GRADE")
    def rollNo = column[Int]("ROLLNO")

    def * = (id, name, grade, rollNo) <> (Student.tupled, Student.unapply)
  }

  private[repository] class StudentInfoTable(tag: Tag) extends Table[StudentInfo](tag, "STUDENTINFO") {

    def id = column[Long]("ID", O.PrimaryKey)
    def age = column[Int]("AGE")
    def subject = column[String]("SUBJECT")

    def * = (id, age, subject) <> (StudentInfo.tupled, StudentInfo.unapply)
  }

}
