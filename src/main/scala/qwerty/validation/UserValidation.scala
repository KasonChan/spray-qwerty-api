package qwerty.validation

import qwerty.models.UserLogin

/**
 * Created by ka-son on 8/8/15.
 */
trait UserValidation {

  def checkLogin(login: String): Option[String] = {
    if ((login.length < 1) || (login.length > 50))
      Some("Username must be at least 1 character and at most 50 characters")
    else
      None
  }

  def checkEmail(email: String): Option[String] = {
    val emailPattern = """([a-zA-Z0-9._]+)@([a-zA-Z0-9._]+)(\.)([a-zA-Z0-9]+)"""

    if (!email.matches(emailPattern))
      Some("Doesn't look like a valid email")
    else
      None
  }

  def checkPassword(password: String): Option[String] = {
    if ((password.length < 8) || (password.length > 50))
      Some("Password must be at least 8 characters and at most 50 characters")
    else
      None
  }

  def validateUserLogin(userLogin: UserLogin): Seq[Option[String]] = {
    val lv = checkLogin(userLogin.login)
    val ev = checkEmail(userLogin.email)
    val pv = checkPassword(userLogin.password)

    val resultSeq: Seq[Option[String]] = Seq(lv, ev, pv)
    val resultSome: Seq[Option[String]] = resultSeq.filter(r => r != None)

    resultSome
  }

}
