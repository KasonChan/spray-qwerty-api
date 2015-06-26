package qwerty.boot

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import qwerty.actors.ServiceSupervisor
import spray.can.Http

import scala.concurrent.duration._

/**
 * Created by ka-son on 6/25/15.
 */
object Boot extends App {

  // Create a actor system to host the application
  implicit val system = ActorSystem("system")

  // Create and start service supervisor
  val service = system.actorOf(Props[ServiceSupervisor], "service")

  implicit val timeout = Timeout(5.seconds)

  // Start a new HTTP server on port 9000 with the service supervisor actor as
  // the handler
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 9000)

}
