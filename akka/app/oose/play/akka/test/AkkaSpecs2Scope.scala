package oose.play.akka.test

import org.specs2.mutable.After
import org.specs2.mutable._

import akka.actor._
import akka.testkit._


/**
 *  A tiny class that can be used as a Specs2 'context'.
 */
abstract class AkkaSpecs2Scope extends TestKit(ActorSystem())
  with After
  with ImplicitSender {
  
  // make sure we shut down the actor system after all tests have run
  def after = system.shutdown()
}