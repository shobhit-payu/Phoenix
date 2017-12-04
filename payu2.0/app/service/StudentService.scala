package service

import model.Student
import repository.StudentRepository
import scala.concurrent.Future

object StudentService{

  def listAllStudents = {
      StudentRepository.listAllStudents
  }

  def findById(id : Long) = {
    StudentRepository.findById(id)
  }

  def addNewStudent(student: Student) = {
    StudentRepository.addNewStudent(student)
  }

  def findNameByRollNo(rollNo : Int) = {
    StudentRepository.findNameByRollNo(rollNo)
  }

  def updateRollNoAndNameById(id : Long, name : String, rollNo : Int) = {
    StudentRepository.updateRollNoAndNameById(id, name, rollNo)
  }

  def deleteByRollNo(rollNo : Int) = {
    StudentRepository.deleteByRollNo(rollNo)
  }

  def selectOnId = {
    StudentRepository.selectOnId
  }

  def nativeSelect(id : Long) = {
    StudentRepository.nativeSelect(id)
  }

  def nativeUpdate(id : Long, name : String) = {
    StudentRepository.nativeUpdate(id, name)
  }
}