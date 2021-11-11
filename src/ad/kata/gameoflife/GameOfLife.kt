package ad.kata.gameoflife

object GameOfLife {

    fun simulateWith(rule: EvolutionRule, seededBoard: Board): Sequence<Board> =
        generateSequence(seededBoard) { it.evolve(rule) }
}

interface Board {
    fun evolve(rule: EvolutionRule): Board
}

class Infinite2DBoard : Board {
    override fun evolve(rule: EvolutionRule) = this
}