package models

import java.time.LocalDateTime

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Users {

  val userList = new ArrayBuffer[User]()
  val userMap: mutable.Map[String, User] = mutable.Map()
  val userTsMap: mutable.Map[String, LocalDateTime] = mutable.Map()

  addUser(User("kireeti", "abc@gmail.com", "123"))

  def addUser(user: User): Unit = {
    userList += user
    userMap += (user.email -> user)
    userTsMap += (user.email -> LocalDateTime.now())
  }

  def getUser(email: String): Option[User] = {
    userMap.get(email)
  }

}
