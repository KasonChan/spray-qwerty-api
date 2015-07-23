package qwerty.controllers

import com.mongodb.casbah.commons.{Imports, MongoDBObject, TypeImports}
import qwerty.db.Boot
import qwerty.models.{Messages, UserLogin}
import qwerty.protocols.MessagesProtocol._
import spray.http.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import spray.json._
import spray.routing.HttpService
import spray.util.LoggingContext

/**
 * Created by kasonchan on 6/26/15.
 */
trait Users extends HttpService with Boot with Application {

  def findAll(implicit log: LoggingContext): HttpResponse = {
    mongoCollUsers.find()
    val result = for {x <- mongoCollUsers} yield x

    log.info(result.mkString("[", " , ", "]"))
    HttpResponse(
      StatusCodes.OK,
      HttpEntity(ContentTypes.`application/json`, result.mkString("[", " , ", "]")))
  }

  def findByLogin(ul: UserLogin): Option[TypeImports.DBObject] = {
    val login = extractLogin(ul)

    mongoCollUsers.findOne(login)
  }

  def create(ul: UserLogin)(implicit log: LoggingContext): HttpResponse = {
    findByLogin(ul) match {
      case Some(u: TypeImports.DBObject) =>
        val messages = Messages(Seq("Login is already existed")).toJson
        HttpResponse(
          StatusCodes.BadRequest,
          HttpEntity(ContentTypes.`application/json`, messages.prettyPrint)
        )
      case None =>
        val userLogin = extractAll(ul)

        val result = mongoCollUsers.insert(userLogin)

        log.info(result.toString)
        HttpResponse(
          StatusCodes.Created,
          HttpEntity(ContentTypes.`application/json`, result.toString))
    }
  }

  def extractAll(userLogin: UserLogin): Imports.DBObject = {
    MongoDBObject("login" -> userLogin.login,
      "email" -> userLogin.email,
      "password" -> userLogin.password,
      "token" -> userLogin.token)
  }

  def extractLogin(userLogin: UserLogin): Imports.DBObject = {
    MongoDBObject("login" -> userLogin.login)
  }

}
