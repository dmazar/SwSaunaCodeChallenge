package org.dmazar.swsauna

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/** Test [Walker] move from start */
class WalkerTestNextMoveFromStart {

    @Nested
    inner class Up {

        @ParameterizedTest
        @ValueSource(chars = ['|', 'A', '+', 'x'])
        fun validChars(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                    -
                    $char
                    @
                    """.trimIndent()
            } else {
                """
                    x
                    $char
                    @
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                assertWalker(
                    expectedCol = 0,
                    expectedRow = 1,
                    expectedDir = Walker.Dir.UP,
                    expectedPath = "@$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

    @Nested
    inner class Down {

        @ParameterizedTest
        @ValueSource(chars = ['|', 'A', '+', 'x'])
        fun validChars(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                    @
                    $char
                    -
                    """.trimIndent()
            } else {
                """
                    @
                    $char
                    x
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                assertWalker(
                    expectedCol = 0,
                    expectedRow = 1,
                    expectedDir = Walker.Dir.DOWN,
                    expectedPath = "@$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

    @Nested
    inner class Left {

        @ParameterizedTest
        @ValueSource(chars = ['-', 'A', '+', 'x'])
        fun validChars(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                    -$char@
                    """.trimIndent()
            } else {
                """
                    x$char@
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                assertWalker(
                    expectedCol = 1,
                    expectedRow = 0,
                    expectedDir = Walker.Dir.LEFT,
                    expectedPath = "@$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

    @Nested
    inner class Right {

        @ParameterizedTest
        @ValueSource(chars = ['-', 'A', '+', 'x'])
        fun validChars(char: Char) {
            // run test for each valid char
            val mapString = if (char == 'x') {
                """
                    @$char-
                    """.trimIndent()
            } else {
                """
                    @${char}x
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                assertWalker(
                    expectedCol = 1,
                    expectedRow = 0,
                    expectedDir = Walker.Dir.RIGHT,
                    expectedPath = "@$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

    @Test
    fun `no path`() {
        val map = WalkingMap.fromString("@\n\nx")
        with(Walker(map)) {
            moveToStart()
            assertThrows(RuntimeException::class.java) {
                nextMove()
            }
        }
    }

    @Test
    fun `multiple paths`() {
        val map = WalkingMap.fromString("-@-\n\nx")
        with(Walker(map)) {
            moveToStart()
            assertThrows(RuntimeException::class.java) {
                nextMove()
            }
        }
    }

}