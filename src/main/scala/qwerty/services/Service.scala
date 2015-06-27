package qwerty.services

import qwerty.controllers._
import spray.routing._
import spray.util.LoggingContext

/**
 * Created by ka-son on 6/25/15.
 */
// This trait defines the qwerty.service behavior independently from the qwerty.service actor
trait Service extends Application with HttpService with Users {

  def route(implicit log: LoggingContext) = {

    path("") {
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
              find
            }
          } ~
            post {
              complete {
                create
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
