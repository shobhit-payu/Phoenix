package injection


import com.google.inject.AbstractModule
import service.{WSService, WSServiceImpl}


/**
  * Created by nilesh.gupta on 2/21/18.
  */
class MyModule extends AbstractModule {
  def configure() = {
    bind(classOf[WSService]).to(classOf[WSServiceImpl])

  }
}