package ad.kata.gameoflife

interface EvolutionRule {
    fun appliedTo(cell: Cell, neighbors: LiveNeighbors): Cell
}

@JvmInline value class LiveNeighbors(val count: Int) {
    init {
        require(count >= 0)
    }
}

@SuppressWarnings("MagicNumber")
object ConwaysOriginalRule : EvolutionRule by ClassicRule(
    survivesWith = { n -> n.count in 2..3 },
    bornWith = { n -> n.count == 3 }
)

class ClassicRule(
    private val survivesWith: (neighbors: LiveNeighbors) -> Boolean,
    private val bornWith: (neighbors: LiveNeighbors) -> Boolean
) : EvolutionRule {

    override fun appliedTo(cell: Cell, neighbors: LiveNeighbors) = when {
        cell.isAlive() && survivesWith(neighbors) -> LiveCell
        cell.isDead() && bornWith(neighbors) -> LiveCell
        else -> DeadCell
    }
}