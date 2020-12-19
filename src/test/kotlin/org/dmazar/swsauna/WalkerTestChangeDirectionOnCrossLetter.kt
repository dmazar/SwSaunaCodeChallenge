package org.dmazar.swsauna

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/** Test [Walker] change direction when on CROSS or letter */
class WalkerTestChangeDirectionOnCrossLetter {

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

        @Test
        fun noPathError() {
            // run test for each valid char
            val mapString =
                """
                    @
                    |x
                    +
                    """.trimIndent()
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                assertThrows(RuntimeException::class.java) {
                    nextMove()
                }
            }
        }

        @Test
        fun multiplePathError() {
            // run test for each valid char
            val mapString =
                """
                     @
                     |x
                    -+-
                    """.trimIndent()
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                assertThrows(RuntimeException::class.java) {
                    nextMove()
                }
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

        @Test
        fun noPathError() {
            // run test for each valid char
            val mapString =
                """
                    @
                    |x
                    A
                    """.trimIndent()
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                assertThrows(RuntimeException::class.java) {
                    nextMove()
                }
            }
        }

        @Test
        fun multiplePathError() {
            // run test for each valid char
            val mapString =
                """
                     @
                     |x
                    -A-
                    """.trimIndent()
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                assertThrows(RuntimeException::class.java) {
                    nextMove()
                }
            }
        }

    }

}