package models

case class User(name: String, email: String, password: String)

object User{
  def apply(email: String, password: String): User = {
    val user = Users.getUser(email)
    if( user.isDefined){
      new User(user.get.name, email, password)
    }else null
  }
}