package qwerty.test

import org.specs2.mutable.Specification
import qwerty.services.Service
import spray.json._
import spray.testkit.Specs2RouteTest

/**
 * Created by ka-son on 6/25/15.
 */
class ServiceSpec extends Specification with Specs2RouteTest with Service {
  def actorRefFactory = system

  "Service" should {

    "return a json message for GET requests to the root path" in {
      Get() ~> route ~> check {
        val expectedResponse =
          """{"messages": ["Welcome to Qwerty API"]}""".parseJson.prettyPrint
        responseAs[String] must contain(expectedResponse)
      }
    }

  }

}
