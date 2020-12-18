package org.dmazar.swsauna

import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/** Test [Walker] move when on CROSS or letter */
class WalkerTestNextMoveOnCrossLetter {

    @Nested
    inner class KeepUp {

        @ParameterizedTest
        @ValueSource(chars = ['|', 'A', '+', 'x'])
        fun validChars(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                     -
                     $char
                    -+-
                     |
                     @
                    """.trimIndent()
            } else {
                """
                     x
                     $char
                    -+-
                     |
                     @
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 1,
                    expectedRow = 1,
                    expectedDir = Walker.Dir.UP,
                    expectedPath = "@|+$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['|', 'A', '+', 'x'])
        fun validCharsCrossAfterStart(char: Char) {
            val mapString = if (char == 'x') {
                """
                    -
                    $char
                    +
                    @
                    """.trimIndent()
            } else {
                """
                    x
                    $char
                    +
                    @
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 0,
                    expectedRow = 1,
                    expectedDir = Walker.Dir.UP,
                    expectedPath = "@+$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

    @Nested
    inner class KeepDown {

        @ParameterizedTest
        @ValueSource(chars = ['|', 'A', '+', 'x'])
        fun validChars(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                     @
                     |
                    -+-
                     $char
                     -
                    """.trimIndent()
            } else {
                """
                     @
                     |
                    -+-
                     $char
                     x
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 1,
                    expectedRow = 3,
                    expectedDir = Walker.Dir.DOWN,
                    expectedPath = "@|+$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['|', 'A', '+', 'x'])
        fun validCharsCrossAfterStart(char: Char) {
            val mapString = if (char == 'x') {
                """
                    @
                    +
                    $char
                    -
                    """.trimIndent()
            } else {
                """
                    @
                    +
                    $char
                    x
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 0,
                    expectedRow = 2,
                    expectedDir = Walker.Dir.DOWN,
                    expectedPath = "@+$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

    @Nested
    inner class KeepLeft {

        @ParameterizedTest
        @ValueSource(chars = ['-', 'A', '+', 'x'])
        fun validChars(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                      |
                    |$char+-@
                      |
                    """.trimIndent()
            } else {
                """
                      |
                    x$char+-@
                      |
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 1,
                    expectedRow = 1,
                    expectedDir = Walker.Dir.LEFT,
                    expectedPath = "@-+$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['-', 'A', '+', 'x'])
        fun validCharsCrossAfterStart(char: Char) {
            val mapString = if (char == 'x') {
                "|$char+@"
            } else {
                "x$char+@"
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 1,
                    expectedRow = 0,
                    expectedDir = Walker.Dir.LEFT,
                    expectedPath = "@+$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

    @Nested
    inner class KeepRight {

        @ParameterizedTest
        @ValueSource(chars = ['-', 'A', '+', 'x'])
        fun validChars(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                      |
                    @-+$char|
                      |
                    """.trimIndent()
            } else {
                """
                      |
                    @-+${char}x
                      |
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 3,
                    expectedRow = 1,
                    expectedDir = Walker.Dir.RIGHT,
                    expectedPath = "@-+$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['-', 'A', '+', 'x'])
        fun validCharsCrossAfterStart(char: Char) {
            val mapString = if (char == 'x') {
                "@+$char|"
            } else {
                "@+${char}x"
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 2,
                    expectedRow = 0,
                    expectedDir = Walker.Dir.RIGHT,
                    expectedPath = "@+$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

    @Nested
    inner class ChangeDirectionCross {

        @ParameterizedTest
        @ValueSource(chars = ['|', 'A', '+', 'x'])
        fun down(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                    @-+
                      $char
                      -
                    """.trimIndent()
            } else {
                """
                    @-+
                      $char
                      x
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 2,
                    expectedRow = 1,
                    expectedDir = Walker.Dir.DOWN,
                    expectedPath = "@-+$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['|', 'A', '+', 'x'])
        fun up(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                      -
                      $char
                    @-+
                    """.trimIndent()
            } else {
                """
                      x
                      $char
                    @-+
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 2,
                    expectedRow = 1,
                    expectedDir = Walker.Dir.UP,
                    expectedPath = "@-+$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['-', 'A', '+', 'x'])
        fun left(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                     @
                     |
                    $char+
                    """.trimIndent()
            } else {
                """
                     @
                     |
                    $char+
                    x
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 0,
                    expectedRow = 2,
                    expectedDir = Walker.Dir.LEFT,
                    expectedPath = "@|+$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['-', 'A', '+', 'x'])
        fun right(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                    @
                    |
                    +$char
                    """.trimIndent()
            } else {
                """
                    @
                    |
                    +$char
                     x
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 1,
                    expectedRow = 2,
                    expectedDir = Walker.Dir.RIGHT,
                    expectedPath = "@|+$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

    @Nested
    inner class ChangeDirectionLetter {

        @ParameterizedTest
        @ValueSource(chars = ['|', 'A', '+', 'x'])
        fun down(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                    @-B
                      $char
                      -
                    """.trimIndent()
            } else {
                """
                    @-B
                      $char
                      x
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 2,
                    expectedRow = 1,
                    expectedDir = Walker.Dir.DOWN,
                    expectedPath = "@-B$char",
                    expectedLetters = if (char == 'A') "BA" else "B",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['|', 'A', '+', 'x'])
        fun up(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                      -
                      $char
                    @-B
                    """.trimIndent()
            } else {
                """
                      x
                      $char
                    @-B
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 2,
                    expectedRow = 1,
                    expectedDir = Walker.Dir.UP,
                    expectedPath = "@-B$char",
                    expectedLetters = if (char == 'A') "BA" else "B",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['-', 'A', '+', 'x'])
        fun left(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                     @
                     |
                    ${char}B
                    """.trimIndent()
            } else {
                """
                     @
                     |
                    ${char}B
                    x
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 0,
                    expectedRow = 2,
                    expectedDir = Walker.Dir.LEFT,
                    expectedPath = "@|B$char",
                    expectedLetters = if (char == 'A') "BA" else "B",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['-', 'A', '+', 'x'])
        fun right(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                    @
                    |
                    B$char
                    """.trimIndent()
            } else {
                """
                    @
                    |
                    B$char
                     x
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 1,
                    expectedRow = 2,
                    expectedDir = Walker.Dir.RIGHT,
                    expectedPath = "@|B$char",
                    expectedLetters = if (char == 'A') "BA" else "B",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

}