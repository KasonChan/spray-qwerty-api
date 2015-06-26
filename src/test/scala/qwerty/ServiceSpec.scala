package qwerty

import org.specs2.mutable.Specification
import qwerty.services.Service
import spray.http.StatusCodes._
import spray.json._
import spray.testkit.Specs2RouteTest

/**
 * Created by ka-son on 6/25/15.
 */
class ServiceSpec extends Specification with Specs2RouteTest with Service {
  def actorRefFactory = system

  "/ must be 200 Ok" in {
    Get() ~> route ~> check {
      response.status must be(OK)
      val expectedResponse = """{"messages":["Welcome to Qwerty API"]}""".parseJson.prettyPrint
      responseAs[String] must beEqualTo(expectedResponse)
    }
  }

  "/notfound must be 404 Not found" in {
    Get("/notfound") ~> route ~> check {
      response.status must be(NotFound)
      val expectedResponse = """{"messages":["Not found"]}""".parseJson.prettyPrint
      responseAs[String] must beEqualTo(expectedResponse)
    }
  }

}
