package injection


import com.google.inject.AbstractModule
import play.api.{Configuration, Environment}
import service.{WSService, WSServiceImpl}


/**
  * Created by nilesh.gupta on 2/21/18.
  */
case class Module(environment: Environment, configuration: Configuration) extends AbstractModule {
  def configure() = {
    bind(classOf[WSService]).to(classOf[WSServiceImpl])

  }
}
