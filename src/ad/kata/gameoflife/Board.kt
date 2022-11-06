package ad.kata.gameoflife

interface Board {
    fun evolve(rule: EvolutionRule): Board
}

data class Infinite2DBoard(
    private val livingCells: Set<Coordinate>
) : Board {

    constructor(livingCells: List<Coordinate>) : this(livingCells.toHashSet())
    constructor(vararg livingCells: Coordinate) : this(livingCells.toHashSet())

    override fun evolve(rule: EvolutionRule) = copy(
        livingCells = survivingCells(rule) + bornCells(rule)
    )

    private fun survivingCells(rule: EvolutionRule) =
        livingCells.filter {
            LiveCell.survives(rule, liveNeighborsOf(it))
        }.toHashSet()

    private fun bornCells(rule: EvolutionRule) =
        adjacentDeadCells().filter {
            DeadCell.isBorn(rule, liveNeighborsOf(it))
        }.toHashSet()

    private fun adjacentDeadCells() =
        livingCells.flatMap(neighborsOf).toHashSet() - livingCells

    private fun liveNeighborsOf(coordinate: Coordinate) = LiveNeighbors(
        neighborsOf(coordinate).count(livingCells::contains)
    )

    val neighborsOf = memoize { c: Coordinate ->
        adjacentCells.map(c::shift)
    }

    companion object {
        private val adjacentCells by lazy {
            hashSetOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)
        }
    }
}

data class Coordinate(val x: Int, val y: Int) {
    fun shift(xy: Pair<Int, Int>) = Coordinate(x + xy.first, y + xy.second)
}

private fun LiveCell.survives(rule: EvolutionRule, neighbors: LiveNeighbors) =
    rule.appliedTo(this, neighbors).isAlive()

private fun DeadCell.isBorn(rule: EvolutionRule, neighbors: LiveNeighbors) =
    rule.appliedTo(this, neighbors).isAlive()

inline fun <T, R> memoize(crossinline f: (T) -> R): (T) -> R {
    val values = mutableMapOf<T, R>()
    return {
        values.getOrPut(it) { f(it) }
    }
}