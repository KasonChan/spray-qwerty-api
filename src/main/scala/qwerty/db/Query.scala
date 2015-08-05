package qwerty.db

import com.mongodb.DBObject
import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.commons.TypeImports
import qwerty.json.Extractor
import qwerty.models.UserLogin

/**
 * Created by kasonchan on 7/23/15.
 */
trait Query extends Boot with Extractor {

  def findAll(coll: MongoCollection): Iterable[TypeImports.DBObject] = {
    coll.find()
    val result = for {x <- coll} yield x
    result
  }

  def findByLogin(coll: MongoCollection)(ul: UserLogin): Option[TypeImports.DBObject] = {
    val login = extractLogin(ul)
    coll.findOne(login)
  }

  def insert(coll: MongoCollection)(ul: DBObject) = {
    coll.insert(ul)
  }

}
