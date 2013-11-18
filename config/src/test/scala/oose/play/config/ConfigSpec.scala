package oose.play.config

import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ConfigSpec extends Specification with Before {
  
  def before = new TestConfiguration

  "The configuration" should {
    val testconfigured = new Configured {
      val aConfig = configured[AConfig]
      val bConfig = configured[BConfig]
    }
    
    "exist for AConfig" in {
      testconfigured.aConfig.a must be equalTo ("Hello")
    }
    "exist for BConfig" in {
      testconfigured.bConfig.b must be equalTo (2)
    }
  }

}

case class AConfig(a: String)

case class BConfig(b: Int)

class TestConfiguration extends Configuration {
  configure {
    AConfig("Hello")
  }
  configure {
    BConfig(2)
  }
}
