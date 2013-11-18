package oose.play.springextension

import akka.actor.{ActorSystem, Props, Extension}
import org.springframework.context.ApplicationContext
/**
 * The Extension implementation.
 */
class SpringExtensionImpl extends Extension {
  var applicationContext: ApplicationContext = _

  /**
   * Used to initialize the Spring application context for the extension.
   * @param applicationContext
   */
  def initialize(implicit applicationContext: ApplicationContext) = {
    this.applicationContext = applicationContext
    this
  }

  /**
   * Create a Props for the specified actorBeanName using the
   * SpringActorProducer class.
   *
   * @param actorBeanName  The name of the actor bean to create Props for
   * @return a Props that will create the named actor bean using Spring
   */
  def props(actorBeanName: String): Props =
    Props(classOf[SpringActorProducer], applicationContext, actorBeanName)

}

object SpringExtensionImpl {

  def apply(system : ActorSystem) (implicit ctx: ApplicationContext ) :  SpringExtensionImpl =  SpringExtension().get(system).initialize
}
