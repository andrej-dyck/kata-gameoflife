package ad.kata.gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class InfiniteBoardTest {

    @Nested
    inner class Coordinates {

        @Test
        fun `adjacent coordinates are the eight neighbors of a coordinate`() {
            assertThat(
                (1 to 1).adjacent()
            ).containsExactlyInAnyOrder(
                0 to 0,
                0 to 1,
                0 to 2,
                1 to 0,
                1 to 2,
                2 to 0,
                2 to 1,
                2 to 2
            )
        }
    }
}