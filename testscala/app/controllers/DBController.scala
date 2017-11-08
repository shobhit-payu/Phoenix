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
        val r = scala.util.Random

        //val timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()))
        val query = "insert into txn_emi_storecard values (?,?,?,?,?)"
        val st = conn.prepareStatement(query)
        st.setInt(1, r.nextInt(1000000))
        st.setString(2, "Rajasthan State")
        st.setInt(3, 192)
        st.setString(4, "JaipurCity")
        st.setTimestamp(5, new Timestamp(System.currentTimeMillis()))
        st.executeUpdate()

      }


      var statement = conn.createStatement()
      val selectQuery = "SELECT * FROM txn_emi_storecard where payu_id < 5000 and payu_id > 1000"
      val rs1 = statement.executeQuery(selectQuery)
      val selectQuery1 = "SELECT * FROM txn_emi_storecard where payu_id = 10000"
      val rs2 = statement.executeQuery(selectQuery1)
      val selectQuery2 = "SELECT * FROM txn_emi_storecard where addedon > '2017-11-06 12:13:23' and payu_id < 1000"
      val rs3 = statement.executeQuery(selectQuery2)
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
