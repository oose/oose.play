package oose.play.actions

import play.api.mvc._
import play.api.mvc.BodyParsers._
import scala.concurrent.Future
import play.api.libs.ws._

class ProxyAction[A](bodyParser: BodyParser[A] = parse.anyContent)(ws: Request[A] => Future[Response], reply: Future[Response] => Future[SimpleResult])
  extends Action[A] {
  /**
   * compose the Action from the ws function and the reply function.
   */
  def apply(request: Request[A]): Future[SimpleResult] = {
    (reply compose ws)(request)
  }

  def parser = bodyParser
}

object ProxyAction {
  def apply[A](bodyParser: BodyParser[A] = parse.anyContent,
    ws: Request[A] => Future[Response],
    reply: Future[Response] => Future[SimpleResult]) =
    new ProxyAction(bodyParser)(ws, reply)
}
