package org.dmazar.swsauna

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.RuntimeException

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

    private val okMapTests = listOf(
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

        // Map 3 - letters may be found on turns
        listOf(
            """
  @---A---+
          |
  x-B-+   |
      |   |
      +---C
            """.trimIndent(),
            "ACB",
            "@---A---+|||C---+|+-B-x"
        ),

        // Map 4 - do not collect letters twice
        listOf(
            """
    +--B--+
    |   +-C-+
 @--A-+ | | |
    | | +-+ D
    +-+     |
            x
            """.trimIndent(),
            "ABCD",
            "@--A-+|+-+|A|+--B--+C|+-+|+-C-+|D|x"
        ),

        // Map 5 - keep direction, even in a compact space
        listOf(
            """
 +-B-+
 |  +C-+
@A+ ++ D
 ++    x
             """.trimIndent(),
            "ABCD",
            "@A+++A|+-B-+C+++C-+Dx"
        )
    )

    @Test
    fun findAndRunOkTests() {
        okMapTests.forEach {
            testWalker(it[0]) {
                findAndRunThePath()
                assertEquals(it[1], letters)
                assertEquals(it[2], path)
            }
        }
    }

    private val errorMapTests = listOf(
        // Map 6 - no start
        """
      -A---+
          |
  x-B-+   C
      |   |
      +---+
             """.trimIndent(),

        // Map 7 - no end
        """
   @--A---+
          |
    B-+   C
      |   |
      +---+
        """.trimIndent(),

        // Map 8 - multiple starts
        """
   @--A-@-+
          |
  x-B-+   C
      |   |
      +---+
        """.trimIndent(),

        // Map 9 - multiple ends
        """
   @--A---+
          |
  x-Bx+   C
      |   |
      +---+
        """.trimIndent(),
    )

    @Test
    fun findAndRunErrorTests() {
        errorMapTests.forEach {
            assertThrows(RuntimeException::class.java) {
                testWalker(it) {
                    findAndRunThePath()
                }
            }
        }
    }


}