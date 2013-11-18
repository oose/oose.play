package oose.play.actions;

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import play.api.http.HeaderNames
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._

/**
 * A simple action to allow Cross Origin Resource Sharing,
 * which allows to set the ACCESS_CONTROL_ALLOW_ORIGIN header.
 *
 * @param allow the value for the header, e.g. "*"
 *
 */
case class CorsAction[A](allow: String = "*")(action: Action[A]) extends Action[A] with HeaderNames {
  def apply(request: Request[A]): Future[SimpleResult] = {

    action(request).map(_.withHeaders(ACCESS_CONTROL_ALLOW_ORIGIN -> allow))
  }

  lazy val parser = action.parser

}