package qwerty.services

import qwerty.controllers._
import qwerty.models.UserLogin
import qwerty.protocols.UserLoginProtocol._
import spray.routing._
import spray.util.LoggingContext

/**
 * Created by ka-son on 6/25/15.
 */
// This trait defines the qwerty.service behavior independently from the qwerty.service actor
trait Service extends Application with HttpService with Users {

  def route(implicit log: LoggingContext) = {

    pathPrefix("api") {
      pathPrefix("v0.1") {
        pathEnd {
          get {
            complete {
              root
            }
          }
        } ~
          pathPrefix("users") {
            pathEnd {
              get {
                complete {
                  getAll
                }
              } ~
                post {
                  entity(as[UserLogin]) { login =>
                    complete {
                      create(login)
                    }
                  }
                } ~
                post {
                  complete {
                    unauthorized
                  }
                }
            }
          }
      } ~
        pathPrefix("") {
          (get | post | put | delete | patch) {
            complete {
              notFound
            }
          }
        }
    } ~
      pathPrefix("") {
        (get | post | put | delete | patch) {
          complete {
            notFound
          }
        }
      }
  }

}
