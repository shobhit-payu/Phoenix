package controllers

import java.sql.{Connection, DriverManager, Timestamp}
import java.util.{Calendar, Date}
import javax.inject.Inject
import java.sql.PreparedStatement
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}
import com.mchange.v2.c3p0.ComboPooledDataSource

/**
  * Created by hemant.agrawal on 25/10/17.
  */
class DBPoolController extends Controller {
  val conn : Connection = cpool()
  def index = Action {
    dbconnection(conn)
    Ok("Success")
  }

  def cpool() : Connection  = {
    val cpds = new ComboPooledDataSource
    cpds.setJdbcUrl("jdbc:mysql://localhost:3306/payu")
    cpds.setUser("root")
    cpds.setPassword("Password@123")
    cpds.setInitialPoolSize(5)
    cpds.setMinPoolSize(10)
    cpds.setAcquireIncrement(5)
    cpds.setMaxPoolSize(150)
    cpds.setIdleConnectionTestPeriod(15)
    cpds.setMaxIdleTime(25)
    cpds.setMaxIdleTimeExcessConnections(10)
    cpds.setPreferredTestQuery("SELECT 1")
    cpds.setMaxConnectionAge(30)
    val conn = cpds.getConnection()
    return conn
  }
  def dbconnection(conn : Connection) =  {
    // val driver = "com.mysql.jdbc.Driver"
    //val url: String = "jdbc:mysql://localhost:3306/payu"
    //val driver = "org.voltdb.jdbc.Driver"
    //val url: String = "jdbc:voltdb://localhost:21212"
    //val driver = "org.h2.Driver"
    //val url: String = "jdbc:h2:tcp://localhost/~/payu;DB_CLOSE_DELAY=10"
    //val url: String = "jdbc:h2:tcp://localhost/mem:payu"
    //val url: String = "jdbc:h2:~/payu"

    //Class.forName(driver)
    //var conn: Connection = DriverManager.getConnection(url,"root","Password@123")
    //var conn: Connection = DriverManager.getConnection(url,"sa","sa")
    var lastPayuId = 0
    for (i <- 1 to 3) {
      val r = scala.util.Random
      val payuId = r.nextInt(1000000)
      lastPayuId = payuId
      val merchantId = r.nextInt(500)
      val txnId = r.nextInt(2000000)
    //val timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()))
    val query = "insert into txn_emi_storecard values (?,?,?,?,?)"
    val st = conn.prepareStatement(query)
    st.setInt(1,payuId)
    st.setString(2, "Rajasthan State")
    st.setInt(3, 192)
    st.setString(4, "JaipurCity")
    st.setTimestamp(5, new Timestamp(System.currentTimeMillis()))
    st.executeUpdate()

    val query1 = "insert into txn_update values (?,?,?,?,?)"
    val st1 = conn.prepareStatement(query1)
    st1.setInt(1,payuId)
    st1.setString(2, "GeneratedTxnid"+txnId)
    st1.setInt(3,merchantId)
    st1.setString(4, "DC")
    st1.setTimestamp(5, new Timestamp(System.currentTimeMillis()))
    st1.executeUpdate()

    val query2 = "insert into txn_info values (?,?,?,?,?)"
    val st2 = conn.prepareStatement(query2)
    st2.setInt(1,payuId)
    st2.setString(2,"GeneratedTxnid"+txnId)
    st2.setInt(3,merchantId)
    st2.setString(4, "Params")
    st2.setTimestamp(5, new Timestamp(System.currentTimeMillis()))
    st2.executeUpdate()

  }

  //println("LastPayuid of the batch "+lastPayuId)
  var statement = conn.createStatement()
  val selectQuery = "SELECT * FROM txn_emi_storecard where payu_id="+lastPayuId
  val rs1 = statement.executeQuery(selectQuery)
  val joinQuery = "SELECT * FROM txn_info ti, txn_update tu where ti.payu_id=tu.payu_id and ti.payu_id ="+lastPayuId
  val rs2 = statement.executeQuery(joinQuery)
  val updateQuery = "UPDATE txn_emi_storecard set card_token=\'Gurgaon\' where payu_id="+lastPayuId
  val rs3 = statement.executeUpdate(updateQuery)
  //conn.close()
  //cpds.close()

  /*    while (rs.next) {
        val city = rs.getString("town")
        val pin = rs.getString("pin")
        val country = rs.getString("country")
        //println("city = %s, pin = %s".format(city,country))
    }
*/


}

}
