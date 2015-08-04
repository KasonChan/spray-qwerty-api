package qwerty.controllers

import qwerty.models.Messages
import qwerty.protocols.MessagesProtocol._
import spray.http.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import spray.json._
import spray.routing.HttpService
import spray.util.LoggingContext

/**
 * Created by kasonchan on 6/27/15.
 */
trait Application extends HttpService {

  def root(implicit log: LoggingContext): HttpResponse = {
    val messages = Messages(Seq("Welcome to Qwerty API")).toJson
    log.info(messages.compactPrint)
    HttpResponse(
      StatusCodes.OK,
      HttpEntity(ContentTypes.`application/json`, messages.prettyPrint)
    )
  }

  def notFound(implicit log: LoggingContext): HttpResponse = {
    val messages = Messages(Seq("Not found")).toJson
    log.info(messages.compactPrint)
    HttpResponse(
      StatusCodes.NotFound,
      HttpEntity(ContentTypes.`application/json`, messages.prettyPrint)
    )
  }

  def unauthorized(implicit log: LoggingContext): HttpResponse = {
    val messages = Messages(Seq("Requires authentication")).toJson
    log.info(messages.compactPrint)
    HttpResponse(
      StatusCodes.BadRequest,
      HttpEntity(ContentTypes.`application/json`, messages.prettyPrint)
    )
  }

}
