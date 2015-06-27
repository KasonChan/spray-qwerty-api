package qwerty.actors

import akka.actor.{Actor, ActorLogging}
import qwerty.models.Messages
import qwerty.protocols.MessagesProtocol._
import qwerty.services.Service
import spray.http.{HttpResponse, StatusCodes, _}
import spray.json._
import spray.routing.ExceptionHandler

/**
 * Created by kasonchan on 6/26/15.
 */
class ServiceActor extends Actor with ActorLogging with Service {

  override def preStart(): Unit = log.info("Prestart")

  override def postStop(): Unit = log.info("Poststop")

  // The HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // This actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = handleTimeouts orElse runRoute(route)

  def handleTimeouts: Receive = {
    case Timedout(x: HttpRequest) =>
      val messages = Messages(Seq("Timeout")).toJson
      log.info(messages.compactPrint)
      sender() ! HttpResponse(StatusCodes.InternalServerError,
        HttpEntity(ContentTypes.`application/json`, messages.prettyPrint))
  }

  implicit def handleExceptions: ExceptionHandler =
    ExceptionHandler {
      case e: Exception =>
        requestUri { uri =>
          val messages = Messages(Seq(e.toString)).toJson
          log.warning(messages.compactPrint, uri)
          complete(StatusCodes.InternalServerError, messages.prettyPrint)
        }
    }

}
