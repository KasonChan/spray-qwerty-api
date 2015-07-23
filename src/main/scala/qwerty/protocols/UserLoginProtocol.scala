package qwerty.protocols

import qwerty.models.UserLogin
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/**
 * Created by kasonchan on 7/22/15.
 */
object UserLoginProtocol extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val userFormat = jsonFormat4(UserLogin)

}
