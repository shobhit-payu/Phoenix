package model


case class Student (
                     id : Long,
                     name : String,
                     grade : Int,
                     rollNo : Int
                   )


case class StudentInfo (
                        id: Long,
                        age : Int,
                        subject : String
                       )