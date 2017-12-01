package exception

trait AppException extends Exception {
  def errorCode: String
  def message: String

  override def fillInStackTrace(): Throwable = {
    this
  }
}
