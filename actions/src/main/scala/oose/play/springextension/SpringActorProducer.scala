package oose.play.springextension

import akka.actor.{ Actor, IndirectActorProducer }
import org.springframework.scala.context.function._

class SpringActorProducer(
  ctx: FunctionalConfigApplicationContext,
  actorBeanName: String) extends IndirectActorProducer {

  override def produce: Actor = ctx.getBean(actorBeanName, classOf[Actor])

  override def actorClass: Class[_ <: Actor] =
    ctx.getType(actorBeanName).asInstanceOf[Class[_ <: Actor]]
}