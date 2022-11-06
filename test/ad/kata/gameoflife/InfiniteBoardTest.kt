package ad.kata.gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class InfiniteBoardTest {

    @Nested
    inner class Equality {

        @Test
        fun `an empty board is the same as any-sized board with all cells dead`() {
            assertThat(
                Infinite2DBoard()
            ).isEqualTo(
                boardFromString(
                    ".....",
                    ".....",
                    ".....",
                    ".....",
                )
            )
        }

        @Test
        fun `equality is defined by living cell coordinates`() {
            val board = Infinite2DBoard(
                Coordinate(0, 0),
                Coordinate(1, 1)
            )

            assertAll({
                assertThat(board).isEqualTo(
                    boardFromString(
                        "#.",
                        ".#",
                    )
                )
            }, {
                assertThat(board).isEqualTo(
                    boardFromString(
                        "#....",
                        ".#...",
                        ".....",
                        ".....",
                    )
                )
            })
        }
    }

    @Nested
    inner class Evolution {

        @Test
        fun `board does not evolve when all cells survive and none are reborn`() {
            val board = Infinite2DBoard(
                Coordinate(1, 1)
            )
            assertThat(
                board.evolve(
                    ClassicRule(survivalPredicate = { true }, birthPredicate = { false })
                )
            ).isEqualTo(
                board
            )
        }

        @Test
        fun `board dies when no cells survive and none are reborn`() {
            val board = Infinite2DBoard(
                Coordinate(1, 1)
            )
            assertThat(
                board.evolve(
                    ClassicRule(survivalPredicate = { false }, birthPredicate = { false })
                )
            ).isEqualTo(
                Infinite2DBoard()
            )
        }

        @Test
        fun `board populates by dead neighbors next to a living cell are being reborn`() {
            val board = Infinite2DBoard(
                Coordinate(1, 1)
            )
            assertThat(
                board.evolve(
                    ClassicRule(survivalPredicate = { false }, birthPredicate = { true })
                )
            ).isEqualTo(
                boardFromString(
                    "###",
                    "#.#",
                    "###"
                )
            )
        }
    }

    @Nested
    inner class Neighbors {

        @Test
        fun `adjacent coordinates are the eight neighbors of a coordinate`() {
            assertThat(
                Infinite2DBoard().neighborsOf(Coordinate(1, 1))
            ).containsExactlyInAnyOrder(
                Coordinate(0, 0),
                Coordinate(0, 1),
                Coordinate(0, 2),
                Coordinate(1, 0),
                Coordinate(1, 2),
                Coordinate(2, 0),
                Coordinate(2, 1),
                Coordinate(2, 2)
            )
        }
    }

    companion object {
        fun boardFromString(vararg cellRows: String) = Infinite2DBoard(
            cellRows.flatMapIndexed { rowIndex, row ->
                row.withIndex()
                    .filterNot { it.value in setOf(' ', '.') }
                    .map { col -> Coordinate(rowIndex, col.index) }
            }
        )
    }
}