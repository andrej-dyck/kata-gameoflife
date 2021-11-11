package ad.kata.gameoflife

interface Board {
    fun evolve(rule: EvolutionRule): Board
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
        livingCells.flatMap { it.adjacent() } - livingCells

    private fun livingNeighborsOf(coordinate: Coordinate) = NumberOfLiveNeighbors(
        coordinate.adjacent().intersect(livingCells).size
    )
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
