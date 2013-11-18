package oose.play.json

import play.api.libs.json._

class JsonTypeFormats[A: Format] {

  implicit lazy val jsonTypeReads = new JsonTypeReads[A]
  implicit lazy val jsonTypeWrites = new JsonTypeWrites[A]

  def writes(a: A) = jsonTypeWrites.writes(a)
  def reads(js: JsValue) = jsonTypeReads.reads(js)
}

class JsonTypeFormatsOps[A: JsonTypeFormats](val self: A) {
  def F = implicitly[JsonTypeFormats[A]]
  def writes() = F.writes(self)
  def reads(js: JsValue) = F.reads(js)
}

trait ToJsonTypeFormatOps {
  implicit def toJsonTypeFormatOps[A: JsonTypeFormats](a: A) = new JsonTypeFormatsOps(a)
}

object implicits extends ToJsonTypeFormatOps 