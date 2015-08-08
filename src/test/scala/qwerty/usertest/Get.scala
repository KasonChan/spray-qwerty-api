package qwerty.usertest

import org.specs2.mutable.Specification
import qwerty.models.Messages
import qwerty.protocols.MessagesProtocol._
import qwerty.services.Service
import spray.http.StatusCodes._
import spray.json._
import spray.testkit.Specs2RouteTest

/**
 * Created by ka-son on 8/8/15.
 */
class Get extends Specification with Specs2RouteTest with Service {

  def actorRefFactory = system

  "/api/v0.1/users must be 200 Ok" in {
    Get("/api/v0.1/users") ~> route ~> check {
      response.status must be(OK)
    }
  }

  "/api/v0.1/users/a must be 404 NotFound" in {
    Get("/api/v0.1/users/a") ~> route ~> check {
      response.status must be(NotFound)
      val expectedResponse = Messages(Seq("Not found")).toJson
      responseAs[String] must beEqualTo(expectedResponse.prettyPrint)
    }
  }

}
