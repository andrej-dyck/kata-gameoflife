package ad.kata.gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
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

    @Nested
    inner class StablePatterns {

        @Test
        @Disabled("not yet implemented")
        fun `small rotated-L pattern must result in stable square in 1 iteration`() {
            val givenBoard = boardFromString(
                ".##.",
                ".#.."
            )
            val stableBoard = boardFromString(
                ".##.",
                ".##."
            )

            `assertThat this is the simulated sequence of boards`(
                givenBoard,
                stableBoard,
                stableBoard
            )
        }

        @Test
        @Disabled("not yet implemented")
        fun `large rotated-L pattern must result in stable circle in 3 iterations`() {
            val givenBoard = boardFromString(
                ".....",
                ".###.",
                ".#...",
                "....."
            )
            val stableBoard = boardFromString(
                ".##..",
                "#..#.",
                ".##..",
                "....."
            )

            `assertThat this is the simulated sequence of boards`(
                givenBoard,
                boardFromString(
                    "..#..",
                    ".##..",
                    ".#...",
                    "....."
                ),
                boardFromString(
                    ".##..",
                    ".##..",
                    ".##..",
                    "....."
                ),
                stableBoard,
                stableBoard
            )
        }

        @Test
        @Disabled("not yet implemented")
        fun `large I pattern must result in stable oval in 3 iterations`() {
            val givenBoard = boardFromString(
                "...#...",
                "...#...",
                "...#...",
                "...#..."
            )
            val stableBoard = boardFromString(
                "...#...",
                "..#.#..",
                "..#.#..",
                "...#..."
            )

            `assertThat this is the simulated sequence of boards`(
                givenBoard,
                boardFromString(
                    ".......",
                    "..###..",
                    "..###..",
                    "......."
                ),
                stableBoard,
                stableBoard
            )
        }
    }

    @Nested
    inner class DyingPatterns {

        private val allCellsAreDeadBoard = boardFromString()

        @Test
        @Disabled("not yet implemented")
        fun `i pattern must result in all dead in 2 iterations`() =
            `assertThat this is the simulated sequence of boards`(
                boardFromString(
                    "..#.",
                    ".#..",
                    ".#.."
                ),
                boardFromString(
                    "....",
                    ".##.",
                    "...."
                ),
                allCellsAreDeadBoard
            )

        @Test
        @Disabled("not yet implemented")
        fun `diagonal line pattern must result in all dead in 2 iterations`() =
            `assertThat this is the simulated sequence of boards`(
                boardFromString(
                    "...#.",
                    "..#..",
                    ".#..."
                ),
                boardFromString(
                    ".....",
                    "..#..",
                    "....."
                ),
                allCellsAreDeadBoard
            )

        @Test
        @Disabled("not yet implemented")
        fun `rotated-i pattern must result in all dead in 5 iterations`() =
            `assertThat this is the simulated sequence of boards`(
                boardFromString(
                    "......",
                    ".#....",
                    "..###.",
                    "......"
                ),
                boardFromString(
                    "......",
                    "..##..",
                    "..##..",
                    "...#.."
                ),
                boardFromString(
                    "......",
                    "..##..",
                    "....#.",
                    "..##.."
                ),
                boardFromString(
                    "......",
                    "...#..",
                    "....#.",
                    "...#.."
                ),
                boardFromString(
                    "......",
                    "......",
                    "...##.",
                    "......"
                ),
                allCellsAreDeadBoard
            )
    }

    @Nested
    inner class OscillatingPatterns {

        @Test
        @Disabled("not yet implemented")
        fun `blinker pattern is a period 2 oscillator`() {
            val seed = boardFromString(
                "..#..",
                "..#..",
                "..#.."
            )

            `assertThat this is the simulated sequence of boards`(
                seed,
                boardFromString(
                    ".....",
                    ".###.",
                    "....."
                ),
                seed
            )
        }

        @Test
        @Disabled("not yet implemented")
        fun `clock pattern is a period 2 oscillator`() {
            val seed = boardFromString(
                "...#..",
                ".##...",
                "...##.",
                "..#..."
            )

            `assertThat this is the simulated sequence of boards`(
                seed,
                boardFromString(
                    "..#...",
                    "..#.#.",
                    ".#.#..",
                    "...#.."
                ),
                seed
            )
        }

        @Test
        @Disabled("not yet implemented")
        fun `pulsar pattern is a period 3 oscillator`() {
            val seed = boardFromString(
                ".................",
                ".................",
                "....###...###....",
                ".................",
                "..#....#.#....#..",
                "..#....#.#....#..",
                "..#....#.#....#..",
                "....###...###....",
                ".................",
                "....###...###....",
                "..#....#.#....#..",
                "..#....#.#....#..",
                "..#....#.#....#..",
                ".................",
                "....###...###....",
                ".................",
                ".................",
                "................."
            )

            `assertThat this is the simulated sequence of boards`(
                seed,
                boardFromString(
                    ".................",
                    ".....#.....#.....",
                    ".....#.....#.....",
                    ".....##...##.....",
                    ".................",
                    ".###..##.##..###.",
                    "...#.#.#.#.#.#...",
                    ".....##...##.....",
                    ".................",
                    ".....##...##.....",
                    "...#.#.#.#.#.#...",
                    ".###..##.##..###.",
                    ".................",
                    ".....##...##.....",
                    ".....#.....#.....",
                    ".....#.....#.....",
                    ".................",
                    "................."
                ),
                boardFromString(
                    ".................",
                    ".................",
                    "....##.....##....",
                    ".....##...##.....",
                    "..#..#.#.#.#..#..",
                    "..###.##.##.###..",
                    "...#.#.#.#.#.#...",
                    "....###...###....",
                    ".................",
                    "....###...###....",
                    "...#.#.#.#.#.#...",
                    "..###.##.##.###..",
                    "..#..#.#.#.#..#..",
                    ".....##...##.....",
                    "....##.....##....",
                    ".................",
                    ".................",
                    "................."
                ),
                seed
            )
        }

        @Test
        @Disabled("not yet implemented")
        fun `glider is a moving period 4 oscillator`() {
            val seed = boardFromString(
                "..#....",
                "...#...",
                ".###...",
                "......."
            )

            `assertThat this is the simulated sequence of boards`(
                seed,
                boardFromString(
                    ".......",
                    ".#.#...",
                    "..##...",
                    "..#...."
                ),
                boardFromString(
                    ".......",
                    "...#...",
                    ".#.#...",
                    "..##..."
                ),
                boardFromString(
                    ".......",
                    "..#....",
                    "...##..",
                    "..##..."
                ),
                boardFromString(
                    ".......",
                    "...#...",
                    "....#..",
                    "..###.."
                )
            )
        }
    }

    private fun `assertThat this is the simulated sequence of boards`(
        seededBoard: Board,
        vararg expectedBoards: Board
    ) {
        assertThat(
            simulateWith(seededBoard).take(expectedBoards.size + 1).toList()
        ).containsExactlyElementsOf(
            listOf(seededBoard) + expectedBoards
        )
    }
}
