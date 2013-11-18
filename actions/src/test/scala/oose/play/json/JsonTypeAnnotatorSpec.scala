package oose.play.json

import org.junit.runner.RunWith
import org.specs2.mutable._
import play.api.libs.functional.syntax._
import play.api.libs.json._
import scalaz._
import scalaz.Scalaz._
import implicits._
import org.specs2.runner.JUnitRunner
import oose.play.utils.id.UUIDGenerator

@RunWith(classOf[JUnitRunner])
class JsonTypeAnnotatorSpec extends Specification {

  implicit val intAnnotator = new JsonTypeFormats[Int]
  implicit val intReader = new JsonTypeReads[Int]
  implicit val stringAnnotator = new JsonTypeFormats[String]
  implicit val personJson: Format[Person] = Json.format[Person]
  implicit val personAnnotator = new JsonTypeFormats[Person]

  "The type annotator" should {
    "write Integers" in {
      val json = 3.writes()
      (json \ "type" \ "className") must be equalTo (JsString("Integer"))
    }

    "write Strings" in {
      val json = "hello".writes()
      (json \ "type" \ "className") must be equalTo (JsString("String"))
    }

    "write Persons" in {
      val json = Person("Dude", 42.0).writes()
      (json \ "type" \ "className") must be equalTo (JsString("Person"))
    }
  }

//  "The type annotator" should {
//    "read Integers" in {
//      val json = 3.writes()
//      val i = json.reads[Integer]
//      i match {
//        case JsSuccess(i, _) => i must beEqualTo(3)
//        case JsError(_) => failure("failed to convert")
//      }
//    }
//  }
}

case class Person(name: String, age: Double) extends UUIDGenerator