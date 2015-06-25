package qwerty.services

import akka.actor.Actor
import spray.json._
import spray.routing._

/**
 * Created by ka-son on 6/25/15.
 */
class ServiceActor extends Actor with Service {

  // The HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // This actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(route)

}

// This trait defines the qwerty.service behavior independently from the qwerty.service actor
trait Service extends HttpService {

  def route = {
    path("") {
      get {
        complete {
          val messages = """{"messages": ["Welcome to Qwerty API"]}""".parseJson
          messages.prettyPrint
        }
      }
    }
  }

}
