package ad.kata.gameoflife

interface EvolutionRule {
    fun applyTo(cell: Cell, liveNeighbors: NumberOfLiveNeighbors): Cell
}

fun EvolutionRule.liveCellSurvivesWith(liveNeighbors: NumberOfLiveNeighbors) =
    applyTo(LiveCell, liveNeighbors) is LiveCell

fun EvolutionRule.deadCellIsRebornWith(liveNeighbors: NumberOfLiveNeighbors) =
    applyTo(DeadCell, liveNeighbors) is LiveCell

@JvmInline
value class NumberOfLiveNeighbors(val amount: Int) {
    init {
        require(amount >= 0)
    }
}

@SuppressWarnings("ExpressionBodySyntax", "MagicNumber")
object ConwaysOriginalRule : EvolutionRule by ClassicRule(
    survivalPredicate = { liveNeighbors -> liveNeighbors.amount in 2..3 },
    birthPredicate = { liveNeighbors -> liveNeighbors.amount == 3 }
)

class ClassicRule(
    private val survivalPredicate: (NumberOfLiveNeighbors) -> Boolean,
    private val birthPredicate: (NumberOfLiveNeighbors) -> Boolean
) : EvolutionRule {

    override fun applyTo(cell: Cell, liveNeighbors: NumberOfLiveNeighbors) = when {
        cell.diesWith(liveNeighbors) -> DeadCell
        cell.isBornWith(liveNeighbors) -> LiveCell
        else -> cell
    }

    private fun Cell.diesWith(liveNeighbors: NumberOfLiveNeighbors) =
        isAlive() && !survivalPredicate(liveNeighbors)

    private fun Cell.isBornWith(liveNeighbors: NumberOfLiveNeighbors) =
        isDead() && birthPredicate(liveNeighbors)
}