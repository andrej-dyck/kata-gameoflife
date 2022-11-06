package ad.kata.gameoflife

interface Board {
    fun evolve(rule: EvolutionRule): Board
}

data class Coordinate(val x: Int, val y: Int) {
    operator fun plus(xy: Pair<Int, Int>) = Coordinate(x + xy.first, y + xy.second)
}

data class Infinite2DBoard(
    private val livingCells: HashSet<Coordinate>
) : Board {

    constructor(vararg livingCells: Coordinate) : this(livingCells.toHashSet())
    constructor(livingCells: List<Coordinate>) : this(livingCells.toHashSet())

    override fun evolve(rule: EvolutionRule) = Infinite2DBoard(
        survivingCells(rule) + rebornCells(rule)
    )

    private fun survivingCells(rule: EvolutionRule) =
        livingCells.filter { rule.liveCellSurvivesWith(livingNeighborsOf(it)) }

    private fun rebornCells(rule: EvolutionRule) =
        deadCellsNeighborsToLiving().filter { rule.deadCellIsRebornWith(livingNeighborsOf(it)) }

    private fun deadCellsNeighborsToLiving() =
        livingCells.flatMap(::neighborsOf) - livingCells

    private fun livingNeighborsOf(coordinate: Coordinate) = NumberOfLiveNeighbors(
        neighborsOf(coordinate).intersect(livingCells).size
    )

    fun neighborsOf(coordinate: Coordinate) =
        adjacentCells.map { coordinate + it }.toHashSet()

    private val adjacentCells by lazy {
        hashSetOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)
    }
}
