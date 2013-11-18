package oose.play.json

import play.api.http._
import play.api.libs.json._
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.api.mvc._

case class StatusMessage[A: Writes](key: String, value: A) {
  def toJson(): JsValue = Json.obj(key -> Json.toJson(value))
}

object StatusMessage {

  implicit def contentTypeOf_StatusMessage(implicit codec: Codec): ContentTypeOf[StatusMessage[_]] = {
    ContentTypeOf[StatusMessage[_]](Some(ContentTypes.JSON))
  }

  implicit def writeableOf_StatusMessage(implicit codec: Codec): Writeable[StatusMessage[_]] = {
    Writeable(sm => codec.encode(sm.toJson().toString))
  }
  
  def error[A: Writes](value: A) = StatusMessage("error", value)
  
  def success[A: Writes](value: A) = StatusMessage("success", value)
}