package qwerty.services

import qwerty.controllers.Users
import qwerty.models.Messages
import qwerty.protocols.MessagesProtocol._
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
          val messages = Messages(Seq("Welcome to Qwerty API")).toJson
          HttpResponse(
            StatusCodes.OK,
            HttpEntity(ContentTypes.`application/json`, messages.prettyPrint)
          )
        }
      }
    } ~
      pathPrefix("users") {
        pathEnd {
          get {
            complete {
              val messages = Messages(Seq("PathPrefix pathEnd get")).toJson
              HttpResponse(
                StatusCodes.NotFound,
                HttpEntity(ContentTypes.`application/json`, messages.prettyPrint))
            }
          } ~
            post {
              complete {
                val messages = Messages(Seq("PathPrefix pathEnd post")).toJson
                HttpResponse(
                  StatusCodes.NotFound,
                  HttpEntity(ContentTypes.`application/json`, messages.prettyPrint))
              }
            }
        } ~
          path("users") {
            get {
              complete {
                val messages = Messages(Seq("PathPrefix users get")).toJson
                HttpResponse(
                  StatusCodes.OK,
                  HttpEntity(ContentTypes.`application/json`, messages.prettyPrint))
              }
            }
          }
      } ~
      pathPrefix("") {
        (get | post | put | delete | patch) {
          complete {
            val messages = Messages(Seq("Not found")).toJson
            //            log.info(messages.compactPrint)
            HttpResponse(
              StatusCodes.NotFound,
              HttpEntity(ContentTypes.`application/json`, messages.prettyPrint))
          }
        }
      }
  }

}
