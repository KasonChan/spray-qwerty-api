package qwerty.json

import com.mongodb.casbah.commons.{Imports, MongoDBObject}
import qwerty.models.UserLogin

/**
 * Created by kasonchan on 7/23/15.
 */
trait Extractor {

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
