package qwerty.actors

import akka.actor.{Actor, ActorLogging}
import qwerty.services.Service

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
  def receive = runRoute(route)

}
