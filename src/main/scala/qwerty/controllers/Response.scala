package qwerty.controllers

import qwerty.models.Messages
import qwerty.protocols.MessagesProtocol._
import spray.http._
import spray.json._
import spray.routing.HttpService
import spray.util.LoggingContext

/**
 * Created by kasonchan on 8/5/15.
 */
trait Response extends HttpService {

  def response(statusCode: StatusCode, messages: Seq[String])(implicit log: LoggingContext): HttpResponse = {
    val msgs = Messages(messages).toJson
    log.info(msgs.compactPrint)

    HttpResponse(
      statusCode,
      HttpEntity(ContentTypes.`application/json`, msgs.prettyPrint)
    )
  }

}
