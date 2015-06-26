package qwerty.services

import qwerty.controllers.Users
import spray.http._
import spray.json._
import spray.routing._

/**
 * Created by ka-son on 6/25/15.
 */
// This trait defines the qwerty.service behavior independently from the qwerty.service actor
trait Service extends HttpService with Users {

  def route = {

    path("") {
      get {
        complete {
          val messages = """{"messages":["Welcome to Qwerty API"]}""".parseJson.prettyPrint
          HttpResponse(
            StatusCodes.OK,
            HttpEntity(ContentTypes.`application/json`, messages)
          )
        }
      }
    } ~
      path("users") {
        get {
          complete {
            val messages = """{"messages":["Users"]}""".parseJson.prettyPrint
            HttpResponse(
              StatusCodes.OK,
              HttpEntity(ContentTypes.`application/json`, messages))
          }
        }
      } ~
      pathPrefix("") {
        get {
          complete {
            val messages = """{"messages":["Not found"]}""".parseJson.prettyPrint
            HttpResponse(
              StatusCodes.NotFound,
              HttpEntity(ContentTypes.`application/json`, messages))
          }
        }
      }
  }

}
