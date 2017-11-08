package controllers

import java.sql.{Connection, DriverManager, Timestamp}
import java.util.{Calendar, Date}
import javax.inject.Inject
import java.sql.PreparedStatement
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}

/**
  * Created by hemant.agrawal on 25/10/17.
  */
class DBController extends Controller {


  def index = Action {
    dbconnection()
    Ok("Success")
  }

  def dbconnection() =  {
      val driver = "com.mysql.jdbc.Driver"
      val url: String = "jdbc:mysql://localhost:3306/payu"
      //val driver = "org.voltdb.jdbc.Driver"
      //val url: String = "jdbc:voltdb://localhost:21212"
     // val driver = "org.h2.Driver"
     // val url: String = "jdbc:h2:tcp://localhost/~/country"
      //val url: String = "jdbc:h2:mem:test"

      val val1 = ""
      val val2 = ""
      Class.forName(driver)
      var conn: Connection = DriverManager.getConnection(url,"root","Password@123")


    for (i <- 1 to 10) {
      import java.text.SimpleDateFormat
      val timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()))
      val query = "insert into towns values (?,?,?)"
      val st = conn.prepareStatement(query)
      st.setString(1, "Jaipur")
      st.setString(2, "Raj")
      st.setString(3, timeStamp)
      st.executeUpdate()

      val stream = "insert into towns_stream values (?,?,?)"
      val sts = conn.prepareStatement(stream)
      sts.setString(1, "Jaipur")
      sts.setString(2, "Raj")
      sts.setString(3, timeStamp)
      sts.executeUpdate()
    }


    var statement = conn.createStatement()
    val selectQuery = "SELECT * FROM towns"
    val rs = statement.executeQuery(selectQuery)
    conn.close()    
/* while (rs.next) {
      val city = rs.getString("town")
      val pin = rs.getString("pin")
      val country = rs.getString("country")
      //println("city = %s, pin = %s".format(city,country))
    }
*/
  }


}
