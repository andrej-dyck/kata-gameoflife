package ad.kata.gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ConwaysOriginalRuleTest {

    private val conwaysRule = ConwaysOriginalRule

    @ParameterizedTest
    @ValueSource(ints = [0, 1])
    fun `any live cell with fewer than two live neighbours dies, as if caused by underpopulation`(
        liveNeighbors: Int
    ) =
        `assertThat it dies`(
            conwaysRule.appliedTo(LiveCell, LiveNeighbors(liveNeighbors))
        )

    @ParameterizedTest
    @ValueSource(ints = [2, 3])
    fun `any live cell with two or three live neighbours lives on to the next generation`(
        liveNeighbors: Int
    ) =
        `assertThat it lives`(
            conwaysRule.appliedTo(LiveCell, LiveNeighbors(liveNeighbors))
        )

    @ParameterizedTest
    @ValueSource(ints = [4, 5, 6, 7, 8])
    fun `any live cell with more than three live neighbours dies, as if by overpopulation`(
        liveNeighbors: Int
    ) =
        `assertThat it dies`(
            conwaysRule.appliedTo(LiveCell, LiveNeighbors(liveNeighbors))
        )

    @Test
    fun `any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction`() =
        `assertThat it lives`(
            conwaysRule.appliedTo(DeadCell, LiveNeighbors(3))
        )

    private fun `assertThat it dies`(cell: Cell) {
        assertThat(cell.isDead()).`as`("cell is dead").isTrue
    }

    private fun `assertThat it lives`(cell: Cell) {
        assertThat(cell.isAlive()).`as`("cell is alive").isTrue
    }
}
