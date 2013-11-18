package oose.play.json

import play.api.libs.json.Writes
import play.api.libs.json.Json
import play.api.libs.json._

class JsonTypeWrites[A: Writes] extends Writes[A] {
  def typeName(a: A) = a.getClass().getSimpleName()
  def writes(a: A): JsValue = {
    val name = typeName(a)
    Json.obj("type" ->
      Json.obj("className" -> Json.toJson(name), "data" -> Json.toJson(a)))
  }
}

class JsonTypeWritesOps[A: JsonTypeWrites](val self: A) {
  val F = implicitly[JsonTypeWrites[A]]
  def writes() = F.writes(self)
}

trait ToJsonTypeWritesOps {
  implicit def toJsonTypeAnnotatorOps[A: JsonTypeWrites](a: A) = new JsonTypeWritesOps(a)
}

object JsonTypeAnnotatorImplicits extends ToJsonTypeWritesOps 