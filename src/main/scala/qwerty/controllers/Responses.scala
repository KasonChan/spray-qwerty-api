package qwerty.controllers

import spray.http._

/**
 * Created by kasonchan on 6/27/15.
 */
trait Responses extends Response {

  def root: HttpResponse = {
    val messages = Seq("Welcome to Qwerty API")
    response(StatusCodes.OK, messages)
  }

  def ok(messages: Seq[String]): HttpResponse = {
    response(StatusCodes.OK, messages)
  }

  def created(messages: Seq[String]): HttpResponse = {
    response(StatusCodes.Created, messages)
  }

  def notFound: HttpResponse = {
    val messages = Seq("Not found")
    response(StatusCodes.NotFound, messages)
  }

  def badRequest(messages: Seq[String]): HttpResponse = {
    response(StatusCodes.BadRequest, messages)
  }

  def unauthorized: HttpResponse = {
    val messages = Seq("Requires authentication")
    response(StatusCodes.Unauthorized, messages)
  }

}
