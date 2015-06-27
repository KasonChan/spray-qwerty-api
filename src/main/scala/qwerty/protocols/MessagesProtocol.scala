package qwerty.protocols

import qwerty.models.Messages
import spray.json.DefaultJsonProtocol

/**
 * Created by kasonchan on 6/26/15.
 */
object MessagesProtocol extends DefaultJsonProtocol {

  implicit val messagesFormat = jsonFormat1(Messages)

}
