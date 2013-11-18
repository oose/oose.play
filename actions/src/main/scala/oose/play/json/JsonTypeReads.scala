package oose.play.json

import play.api.libs.json.Reads
import play.api.libs.json._

class JsonTypeReads[A: Reads]  extends Reads[A]{
  def reads(json: JsValue): play.api.libs.json.JsResult[A] = {
    val reader = implicitly[Reads[A]]
    (json \ "type" \ "data").asOpt[JsValue] match {
      case Some(data) => reader.reads(data)
      case _ => JsError("No data structure found.")
    }
  } 
}

class JsonTypeReadsOps[A: Reads](val self: JsValue) {
  def reads() = implicitly[Reads[A]].reads(self)
}

trait ToJsonTypeReadsOps {
  implicit def jsonToJsonTypeReaderOps[A: Reads](jsValue: JsValue) = new JsonTypeReadsOps[A](jsValue)
}

object tojsontypereadsops extends ToJsonTypeReadsOps