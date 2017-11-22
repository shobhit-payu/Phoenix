package exception

trait AppException extends Exception {
  def errorCode: Int
  def message: String

  override def fillInStackTrace(): Throwable = {
    this
  }
}
