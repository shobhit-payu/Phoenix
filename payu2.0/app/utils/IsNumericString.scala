package utils

/**
  * Created by pragya.mishra on 1/5/18.
  */
object IsNumericString {

  def check ( s : String) : Boolean = {
    s.matches("\\d+(\\.\\d+)?")
  }
}
