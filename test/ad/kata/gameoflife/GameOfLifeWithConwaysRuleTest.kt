package ad.kata.gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GameOfLifeWithConwaysRuleTest {

    private fun simulateWith(seed: Board) =
        GameOfLife.simulateWith(ConwaysOriginalRule, seed)

    @SuppressWarnings("UnusedPrivateMember")
    fun boardFromString(vararg cellRows: String): Board = Infinite2DBoard()

    @Nested
    inner class StillLife {

        @Test
        fun `square must not change`() = `assertThat this board never changes`(
            boardFromString(
                ".##.",
                ".##."
            )
        )

        @Test
        fun `boat must not change`() = `assertThat this board never changes`(
            boardFromString(
                ".##..",
                ".#.#.",
                "..#.."
            )
        )

        @Test
        fun `loaf must not change`() = `assertThat this board never changes`(
            boardFromString(
                "..##..",
                ".#..#.",
                ".#.#..",
                "..#..."
            )
        )

        @Test
        fun `oval must not change`() = `assertThat this board never changes`(
            boardFromString(
                "..#..",
                ".#.#.",
                ".#.#.",
                "..#.."
            )
        )

        @Test
        fun `ship must not change`() = `assertThat this board never changes`(
            boardFromString(
                ".##..",
                ".#.#.",
                "..##."
            )
        )

        @Test
        fun `dead board must remain dead`() = `assertThat this board never changes`(
            boardFromString(
                "...",
                "...",
                "..."
            )
        )

        private fun `assertThat this board never changes`(board: Board, cycles: Int = 3) {
            assertThat(
                simulateWith(board).take(cycles).toList()
            ).containsOnly(board)
        }
    }
}
