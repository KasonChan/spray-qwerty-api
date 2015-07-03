package qwerty.controllers

import com.mongodb.casbah.commons.MongoDBObject
import qwerty.db.Boot
import spray.http.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import spray.routing.HttpService
import spray.util.LoggingContext

/**
 * Created by kasonchan on 6/26/15.
 */
trait Users extends HttpService with Boot {

  def findAll(implicit log: LoggingContext): HttpResponse = {
    mongoCollUsers.find()
    val result = for {x <- mongoCollUsers} yield x

    log.info(result.mkString("[", " , ", "]"))
    HttpResponse(
      StatusCodes.OK,
      HttpEntity(ContentTypes.`application/json`, result.mkString("[", " , ", "]")))
  }

  def create(implicit log: LoggingContext): HttpResponse = {
    val o = MongoDBObject("hello" -> "world")
    mongoCollUsers.insert(o)
    val messages = o.toString

    log.info(messages)
    HttpResponse(
      StatusCodes.Created,
      HttpEntity(ContentTypes.`application/json`, messages))
  }

}
