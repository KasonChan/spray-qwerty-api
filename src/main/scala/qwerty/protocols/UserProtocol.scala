package qwerty.protocols

import qwerty.models.User
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/**
 * Created by ka-son on 6/28/15.
 */
object UserProtocol extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val userFormat = jsonFormat11(User)

}
