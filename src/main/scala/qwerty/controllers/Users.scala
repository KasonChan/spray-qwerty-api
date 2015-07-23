package qwerty.controllers

import com.mongodb.util.JSON
import qwerty.db.Boot
import qwerty.models.UserLogin
import qwerty.protocols.UserLoginProtocol._
import spray.http.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import spray.json._
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

  def create(userLogin: UserLogin)(implicit log: LoggingContext): HttpResponse = {
    val ul = userLogin.toJson.compactPrint

    val ulDBObject = JSON.parse(ul)
    mongoCollUsers.insert(ul)

    log.info(ul)
    HttpResponse(
      StatusCodes.Created,
      HttpEntity(ContentTypes.`application/json`, ul))
  }

}
