package qwerty.controllers

import com.mongodb.casbah.commons.TypeImports
import qwerty.db.{Boot, Query}
import qwerty.json.Extractor
import qwerty.models.{Messages, UserLogin}
import qwerty.protocols.MessagesProtocol._
import spray.http.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import spray.json._
import spray.util.LoggingContext

/**
 * Created by kasonchan on 6/26/15.
 */
trait Users extends Boot with Application with Query with Extractor {

  def getAll(implicit log: LoggingContext): HttpResponse = {
    val result = findAll(mongoCollLogins)

    result.isEmpty match {
      case true =>
        notFound
      case false =>
        log.info(result.mkString("[", " , ", "]"))
        HttpResponse(
          StatusCodes.OK,
          HttpEntity(ContentTypes.`application/json`, result.mkString("[", " , ", "]")))
    }
  }

  def create(ul: UserLogin)(implicit log: LoggingContext): HttpResponse = {
    findByLogin(mongoCollLogins)(ul) match {
      case Some(u: TypeImports.DBObject) =>
        val messages = Messages(Seq("Login is already existed")).toJson
        log.info(messages.compactPrint)
        HttpResponse(
          StatusCodes.BadRequest,
          HttpEntity(ContentTypes.`application/json`, messages.prettyPrint)
        )
      case None =>
        val userLogin = extractAll(ul)
        val result = insert(mongoCollLogins)(userLogin)
        log.info(result.toString)
        HttpResponse(
          StatusCodes.Created,
          HttpEntity(ContentTypes.`application/json`, result.toString))
    }
  }

}
