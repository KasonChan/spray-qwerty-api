package qwerty.controllers

import com.mongodb.casbah.commons.MongoDBObject
import qwerty.db.Boot
import qwerty.models.Messages
import qwerty.protocols.MessagesProtocol._
import spray.http.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import spray.json._
import spray.routing.HttpService
import spray.util.LoggingContext

/**
 * Created by kasonchan on 6/26/15.
 */
trait Users extends HttpService with Boot {

  def find(implicit log: LoggingContext): HttpResponse = {
    //    val messages = Messages(Seq("Find")).toJson

    mongoCollUsers.find()
    val result = for {x <- mongoCollUsers} yield x

    val messages = Messages(Seq(result.toString)).toJson

    log.info(messages.compactPrint)
    HttpResponse(
      StatusCodes.OK,
      HttpEntity(ContentTypes.`application/json`, messages.prettyPrint))
  }

  def create(implicit log: LoggingContext): HttpResponse = {
    //    val messages = Messages(Seq("Create"))

    val o = MongoDBObject("hello" -> "world")
    val messages = o.toString
    println(mongoCollUsers.insert(o))

    log.info(messages)
    HttpResponse(
      StatusCodes.Created,
      HttpEntity(ContentTypes.`application/json`, messages))
  }

}
