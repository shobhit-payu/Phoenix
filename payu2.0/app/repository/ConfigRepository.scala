package repository

import java.sql.Timestamp

import db.DBComponent
import slick.jdbc.MySQLProfile.api._
import model.Config

/**
  * Created by pragya.mishra on 1/3/18.
  */

object ConfigRepository extends DBComponent
{

  private [repository] val config = TableQuery[ConfigTable]

  def getValueForKey(key : String) = {
    db.run(config.filter(_.key === key).map(_.value).result)
  }

  private[repository] class ConfigTable(tag: Tag) extends Table[Config](tag, "CONFIG") {

    def id = column[Int]("id", O.AutoInc, O.PrimaryKey)
    def key = column[String]("key")
    def value = column[String]("value")
    def version = column[Int]("version")
    def isActive = column[Int]("isactive")
    def addedOn = column[Timestamp]("addedon")
    def updatedOn = column[Timestamp]("updatedon")

    def * = (id, key, value, version, isActive, addedOn, updatedOn) <> (Config.tupled, Config.unapply)
  }


}