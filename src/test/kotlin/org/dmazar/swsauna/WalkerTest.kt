package org.dmazar.swsauna

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Test [Walker] */
internal class WalkerTest {

    @Test
    fun moveToStart() {
        testWalker(
            """
                -
                      @-A
                x-B-+   C
                    |   |
                    +---+
                """.trimIndent()
        ) {
            moveToStart()
            assertWalker(
                expectedCol = 6,
                expectedRow = 1,
                expectedDir = Walker.Dir.UNKNOWN,
                expectedPath = "@",
                expectedLetters = "",
                expectedIsEnd = false
            )
        }

        testWalker(
            """
                       -A
                x-B-+   C
                    |   |
                    @ --+
                """.trimIndent()
        ) {
            moveToStart()
            assertWalker(
                expectedCol = 4,
                expectedRow = 3,
                expectedDir = Walker.Dir.UNKNOWN,
                expectedPath = "@",
                expectedLetters = "",
                expectedIsEnd = false
            )
        }
    }

    @Test
    fun isEnd() {
        testWalker(
            """
                @
                x
                """.trimIndent()
        ) {
            moveToStart()
            nextMove()
            assertWalker(
                expectedCol = 0,
                expectedRow = 1,
                expectedDir = Walker.Dir.DOWN,
                expectedPath = "@x",
                expectedLetters = "",
                expectedIsEnd = true
            )

            // next move does not happen
            nextMove()
            assertWalker(
                expectedCol = 0,
                expectedRow = 1,
                expectedDir = Walker.Dir.DOWN,
                expectedPath = "@x",
                expectedLetters = "",
                expectedIsEnd = true
            )
        }
    }

    private val fullMapTests = listOf(
        // Map 1 - a basic example
        listOf(
            """
  @---A---+
          |
  x-B-+   C
      |   |
      +---+
                      """.trimIndent(),
            "ACB",
            "@---A---+|C|+---+|+-B-x"
        ),

        // Map 2 - go straight through intersections
        listOf(
            """
  @
  | +-C--+
  A |    |
  +---B--+
    |      x
    |      |
    +---D--+
                          """.trimIndent(),
            "ABCD",
            "@|A+---B--+|+--C-+|-||+---D--+|x"
        ),

    )

    @Test
    fun findAndRunThePath() {
        fullMapTests.forEach {
            testWalker(it[0]) {
                findAndRunThePath()
                assertEquals(it[1], letters)
                assertEquals(it[2], path)
            }
        }
    }


}