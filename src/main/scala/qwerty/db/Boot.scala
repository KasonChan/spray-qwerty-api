package qwerty.db

import com.mongodb.casbah.Imports._

/**
 * Created by ka-son on 6/28/15.
 */
trait Boot {

  val server = new ServerAddress("45.55.86.67", 27017)

  val credentials = MongoCredential.createMongoCRCredential("qwertyReadWrite",
    "qwerty",
    Array('r', 'e', 'a', 'd', 'W', 'r', 'i', 't', 'e', 'Q', 'w', 'e', 'r', 't', 'y'))

  val mongoClient = MongoClient(server, List(credentials))

  val mongoDB = mongoClient("qwerty")

  val mongoCollLogins = mongoDB("logins")

  val mongoCollInfos = mongoDB("infos")

  val mongoCollRooms = mongoDB("rooms")

}
