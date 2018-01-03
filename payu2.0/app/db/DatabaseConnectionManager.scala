package db

import slick.jdbc.MySQLProfile

trait  DBComponent {
  val driver = MySQLProfile
  import driver.api._

  val db: Database = MySqlDB.connectionPool
}

private[db] object MySqlDB {
  import slick.jdbc.MySQLProfile.api._

  val connectionPool = Database.forConfig("db.mysql")

}