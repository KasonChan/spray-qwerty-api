package qwerty.models

/**
 * Created by kasonchan on 6/26/15.
 */
case class Login(login: String,
                 email: String,
                 password: String,
                 token: String)

case class Info(login: String,
                name: String,
                category: String,
                location: String,
                confirmed: Boolean,
                created_at: String,
                updated_at: String,
                last_login: String)
