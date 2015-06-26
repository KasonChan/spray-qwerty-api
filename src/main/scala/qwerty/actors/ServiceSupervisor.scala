package qwerty.actors

import akka.actor.SupervisorStrategy._
import akka.actor.{Actor, ActorLogging, OneForOneStrategy, Props}
import akka.routing.{ActorRefRoutee, Router, SmallestMailboxRoutingLogic}

import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * Created by kasonchan on 6/26/15.
 */
class ServiceSupervisor extends Actor with ActorLogging {

  // Override supervisor strategy
  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 5 minute) {
      case _: ArithmeticException => Resume
      case _: NullPointerException => Restart
      case _: IllegalArgumentException => Restart
      case _: Exception => Restart
    }

  // Create router fill with 2 service actor
  var router = {
    val routees = Vector.fill(2) {
      val r = context.actorOf(Props[ServiceActor])
      context watch r
      ActorRefRoutee(r)
    }
    Router(SmallestMailboxRoutingLogic(), routees)
  }

  override def preStart(): Unit = log.info("Prestart")

  override def postStop(): Unit = log.info("Poststop")

  // The HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // Route io http to the children
  def receive = {
    case x =>
      log.info(x.toString)
      router.route(x, sender())
  }

}
