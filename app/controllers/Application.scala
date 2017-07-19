package controllers

import javax.inject.{Inject, Singleton}

import models.{User, Users}
import play.api.data.Forms._
import play.api.data._
import play.api.i18n.Lang
import play.api.mvc._

@Singleton
class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  val userSignForm = Form(
    mapping(
      "name" -> text,
      "email" -> email,
      "password" -> text
    )(User.apply)(User.unapply)
  )

  val userLogForm = Form(
    mapping(
      "email" -> email,
      "password" -> text
    )(User.apply)(user => Option((user.email, user.password))) verifying ("Invalid email or password", result => result match {
      case user => user != null
    })
  )

  def login() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.login(userLogForm))
  }

  def signup() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.signup(userSignForm))
  }

  def post(form: Form[User]): Action[User] = {
    Action(parse.form(form, onErrors = (formWithErrors: Form[User]) => {
      implicit val messages = messagesApi.preferred(Seq(Lang.defaultLang))
      BadRequest(views.html.login(formWithErrors))
    })) { implicit request: Request[User] =>
      val userData = request.body
      val newUser = models.User(userData.name, userData.email, userData.password)
      Users.addUser(newUser)
      Redirect(routes.Application.success(userData.name, Users.userTsMap(userData.email).toString))
    }
  }

  val userPost: Action[User] = post(userSignForm)

  val userLogin: Action[User] = post(userLogForm)

  def success(name: String, ts: String) = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.success(name)(ts.toString))
  }
}

