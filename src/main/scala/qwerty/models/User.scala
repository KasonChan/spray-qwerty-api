package qwerty.models

/**
 * Created by kasonchan on 6/26/15.
 */
case class User(login: String,
                email: String,
                password: String,
                token: String,
                name: String,
                category: String,
                location: String,
                confirmed: Boolean,
                created_at: String,
                updated_at: String,
                last_login: String)
