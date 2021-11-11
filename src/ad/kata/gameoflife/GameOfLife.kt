package ad.kata.gameoflife

object GameOfLife {

    fun simulateWith(rule: EvolutionRule, seededBoard: Board): Sequence<Board> =
        generateSequence(seededBoard) { it.evolve(rule) }
}
