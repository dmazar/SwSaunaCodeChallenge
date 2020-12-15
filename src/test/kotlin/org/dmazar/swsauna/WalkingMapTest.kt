package org.dmazar.swsauna

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.random.Random

/** Test [WalkingMap] */
internal class WalkingMapTest {

    @Nested
    inner class CreateFromString {

        @Test
        fun `exception when empty`() {
            assertThrows(RuntimeException::class.java) {
                WalkingMap.fromString("")
            }
        }

        @Test
        fun `exception when no start`() {
            assertThrows(RuntimeException::class.java) {
                WalkingMap.fromString("---A---+\n    x---+")
            }
        }

        @Test
        fun `exception when multiple start`() {
            assertThrows(RuntimeException::class.java) {
                WalkingMap.fromString("@---A---+\n    x--@-+")
            }
        }

        @Test
        fun `exception when no end`() {
            assertThrows(RuntimeException::class.java) {
                WalkingMap.fromString("@---A---+\n    ---+")
            }
        }

        @Test
        fun `exception when multiple end`() {
            assertThrows(RuntimeException::class.java) {
                WalkingMap.fromString("@---A---+\n    x--x-+")
            }
        }

        @Test
        fun `split to lines`() {
            val lines = arrayOf(
                "@---A---+",
                "        |",
                "x-B-+   C",
                "    |   |",
                "    +---+",
            )
            val stringMap = """
                @---A---+
                        |
                x-B-+   C
                    |   |
                    +---+
            """.trimIndent()
            val map = WalkingMap.fromString(stringMap)
            assertTrue(lines.contentEquals(map.map))
        }

    }

    @Test
    fun numRowsNumCols() {
        repeat(5) {
            // random rows and cols
            val nRows = Random.nextInt(1, 10)
            val nCols = IntArray(nRows) {
                // first row with only 2 chars for start and end
                if (it == 0) {
                    2
                } else {
                    Random.nextInt(2, 10)
                }
            }
            // create map as string
            val mapString = nCols.mapIndexed() { i, nCol ->
                // first row with only 2 chars for start and end
                if (i == 0) {
                    "@x"
                } else {
                    "c".repeat(nCol)
                }
            }.joinToString("\n")

            // create map from it and test it
            with(WalkingMap.fromString(mapString)) {
                assertEquals(nRows, numRows)
                nCols.forEachIndexed { i, ncol ->
                    assertEquals(ncol, numCols[i])
                }
            }
        }
    }

    @Nested
    inner class Is {

        private val map = WalkingMap.fromString("@|-\n+x")

        @Test
        fun start() {
            with(map) {
                for (r in 0 until numRows) {
                    for (c in 0 until numCols[r]) {
                        assertEquals(c == 0 && r == 0, isStart(c, r))
                    }
                }
            }
        }

        @Test
        fun end() {
            with(map) {
                for (r in 0 until numRows) {
                    for (c in 0 until numCols[r]) {
                        assertEquals(c == 1 && r == 1, isEnd(c, r))
                    }
                }
            }
        }

        @Test
        fun vert() {
            with(map) {
                for (r in 0 until numRows) {
                    for (c in 0 until numCols[r]) {
                        assertEquals(c == 1 && r == 0, isVert(c, r))
                    }
                }
            }
        }

        @Test
        fun hor() {
            with(map) {
                for (r in 0 until numRows) {
                    for (c in 0 until numCols[r]) {
                        assertEquals(c == 2 && r == 0, isHor(c, r))
                    }
                }
            }
        }

        @Test
        fun cross() {
            with(map) {
                for (r in 0 until numRows) {
                    for (c in 0 until numCols[r]) {
                        assertEquals(c == 0 && r == 1, isCross(c, r))
                    }
                }
            }
        }

    }
}