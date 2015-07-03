package qwerty

import org.specs2.mutable.Specification
import qwerty.models.Messages
import qwerty.protocols.MessagesProtocol._
import qwerty.services.Service
import spray.http.StatusCodes._
import spray.json._
import spray.testkit.Specs2RouteTest

/**
 * Created by ka-son on 6/25/15.
 */
class ServiceSpec extends Specification with Specs2RouteTest with Service {

  def actorRefFactory = system

  "/ must be 404 Not found" in {
    Get() ~> route ~> check {
      response.status must be(NotFound)
      val expectedResponse = Messages(Seq("Not found")).toJson
      responseAs[String] must beEqualTo(expectedResponse.prettyPrint)
    }
  }

  "/api/v0.1 must be 200" in {
    Get("/api/v0.1") ~> route ~> check {
      response.status must be(OK)
      val expectedResponse = Messages(Seq("Welcome to Qwerty API")).toJson
      responseAs[String] must beEqualTo(expectedResponse.prettyPrint)
    }
  }

  "/notfound must be 404 Not found" in {
    Get("/notfound") ~> route ~> check {
      response.status must be(NotFound)
      val expectedResponse = Messages(Seq("Not found")).toJson
      responseAs[String] must beEqualTo(expectedResponse.prettyPrint)
    }
  }

}
