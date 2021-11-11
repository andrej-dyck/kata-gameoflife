package ad.kata.gameoflife

interface Board {
    fun evolve(rule: EvolutionRule): Board
}

class Infinite2DBoard : Board {
    override fun evolve(rule: EvolutionRule) = this
}

typealias Coordinate = Pair<Int, Int>

@SuppressWarnings("LongMethod")
fun Coordinate.adjacent() = hashSetOf(
    first - 1 to second - 1,
    first - 1 to second,
    first - 1 to second + 1,
    first to second - 1,
    first to second + 1,
    first + 1 to second - 1,
    first + 1 to second,
    first + 1 to second + 1,
)
