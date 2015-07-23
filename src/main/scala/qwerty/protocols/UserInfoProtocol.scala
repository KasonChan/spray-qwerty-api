package qwerty.protocols

import qwerty.models.UserInfo
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/**
 * Created by ka-son on 6/28/15.
 */
object UserInfoProtocol extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val userFormat = jsonFormat9(UserInfo)

}
