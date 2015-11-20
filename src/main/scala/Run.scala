class UserNotFoundException(userId: Int) extends RuntimeException("User not found")
class InvalidUserRecord(userId: Int) extends RuntimeException("Invalid user record")

object Lib {
  case class User(name: String, netSalary: String)
  val database = Map("1" -> User("John", "27"), "2" -> ("Alice", "25"))
  def gross(amount: Int): Double = amount * 1.23
}

case class CorrectUser(id: Int, name: String, netSalary: Int) {
  def displayGrossSalary = println(s"${this.name}: ${Lib.gross(netSalary)}")
}

object CorrectUser {
  def apply(userId: Int, name: String, netSalary: String): CorrectUser = {
    val salary: Int = try {
      netSalary.toInt
    } catch {
      case e: NumberFormatException => throw new InvalidUserRecord(userId)
    }
    CorrectUser(userId, name, salary)
  }
}

object Run {
 import Lib._

  def displayGrossSalary(userId: Int): Unit = {
    val user =
      database.get(userId.toString).map { user =>
        user match {
          case user: (_, _) =>
            (user._1, user._2) match {
              case (name: String, netSalary: String) => CorrectUser(userId, name, netSalary)
              case _ => throw new InvalidUserRecord(userId)
            }
          case user: User => CorrectUser(userId, user.name, user.netSalary)
          case _ => throw new InvalidUserRecord(userId)
        }
      }.getOrElse(throw new UserNotFoundException(userId))

    user.displayGrossSalary
  }

  def main(args: Array[String]) {
    displayGrossSalary(1)
    displayGrossSalary(2)
  }
}