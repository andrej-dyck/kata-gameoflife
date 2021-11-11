package ad.kata.gameoflife

sealed interface Cell

object LiveCell : Cell
object DeadCell : Cell

fun Cell.isAlive() = this is LiveCell
fun Cell.isDead() = this is DeadCell
