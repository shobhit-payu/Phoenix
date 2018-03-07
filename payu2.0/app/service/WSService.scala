package service

/**
  * Created by nilesh.gupta on 2/23/18.
  */
trait WSService {
  def networkCall(message: String): Unit
}
