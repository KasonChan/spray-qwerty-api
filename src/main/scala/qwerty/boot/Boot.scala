package qwerty.boot

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.routing.SmallestMailboxPool
import akka.util.Timeout
import qwerty.actors.ServiceActor
import spray.can.Http

import scala.concurrent.duration._

/**
 * Created by ka-son on 6/25/15.
 */
object Boot extends App {

  // Create a actor system to host the application
  implicit val system = ActorSystem("system")

  // Create and start qwerty.service actor routing
  val service = system.actorOf(SmallestMailboxPool(2).props(Props[ServiceActor]),
    "service")

  implicit val timeout = Timeout(5.seconds)

  // Start a new HTTP server on port 9000 with our qwerty.service actor as the handler
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 9000)

}
