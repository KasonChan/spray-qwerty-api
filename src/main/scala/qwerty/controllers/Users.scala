package qwerty.controllers

import qwerty.models.Messages
import qwerty.protocols.MessagesProtocol._
import spray.http.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import spray.json._
import spray.routing.HttpService
import spray.util.LoggingContext

/**
 * Created by kasonchan on 6/26/15.
 */
trait Users extends HttpService {

  def find(implicit log: LoggingContext): HttpResponse = {
    val messages = Messages(Seq("Find")).toJson
    log.info(messages.compactPrint)
    HttpResponse(
      StatusCodes.OK,
      HttpEntity(ContentTypes.`application/json`, messages.prettyPrint))
  }

  def create(implicit log: LoggingContext): HttpResponse = {
    val messages = Messages(Seq("Create")).toJson
    log.info(messages.compactPrint)
    HttpResponse(
      StatusCodes.Created,
      HttpEntity(ContentTypes.`application/json`, messages.prettyPrint))
  }

}
