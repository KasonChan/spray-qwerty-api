package qwerty.services

import spray.http.{HttpResponse, MediaTypes, StatusCodes}
import spray.json._
import spray.routing._

/**
 * Created by ka-son on 6/25/15.
 */
// This trait defines the qwerty.service behavior independently from the qwerty.service actor
trait Service extends HttpService {

  def route = {

    path("") {
      get {
        respondWithMediaType(MediaTypes.`application/json`) {
          complete {
            val messages = """{"messages":["Welcome to Qwerty API"]}""".parseJson.prettyPrint
            HttpResponse(StatusCodes.OK, messages)
          }
        }
      }
    } ~
      pathPrefix("") {
        get {
          respondWithMediaType(MediaTypes.`application/json`) {
            complete {
              val messages = """{"messages":["Not found"]}""".parseJson.prettyPrint
              HttpResponse(StatusCodes.NotFound, messages)
            }
          }
        }
      }
  }

}
