package org.dmazar.swsauna

import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/** Test [Walker] move when on VERT, HOR, letter or END */
class WalkerTestNextMoveOnVertHorLetter {

    @Nested
    inner class Up {

        @ParameterizedTest
        @ValueSource(chars = ['|', 'A', '+', 'x'])
        fun validCharsFromVert(char: Char) {
            val mapString = if (char == 'x') {
                """
                    -
                    $char
                    |
                    @
                    """.trimIndent()
            } else {
                """
                    x
                    $char
                    |
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
                    expectedPath = "@|$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['|', 'B', '+', 'x'])
        fun validCharsFromLetter(char: Char) {
            val mapString = if (char == 'x') {
                """
                    -
                    $char
                    A
                    @
                    """.trimIndent()
            } else {
                """
                    x
                    $char
                    A
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
                    expectedPath = "@A$char",
                    expectedLetters = if (char == 'B') "AB" else "A",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

    @Nested
    inner class Down {

        @ParameterizedTest
        @ValueSource(chars = ['|', 'A', '+', 'x'])
        fun validCharsFromVert(char: Char) {
            val mapString = if (char == 'x') {
                """
                    @
                    |
                    $char
                    -
                    """.trimIndent()
            } else {
                """
                    @
                    |
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
                    expectedPath = "@|$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['|', 'B', '+', 'x'])
        fun validCharsFromLetter(char: Char) {
            val mapString = if (char == 'x') {
                """
                    @
                    A
                    $char
                    -
                    """.trimIndent()
            } else {
                """
                    @
                    A
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
                    expectedPath = "@A$char",
                    expectedLetters = if (char == 'B') "AB" else "A",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

    @Nested
    inner class Left {

        @ParameterizedTest
        @ValueSource(chars = ['-', 'A', '+', 'x'])
        fun validCharsFromHor(char: Char) {
            val mapString = if (char == 'x') {
                """
                    |$char-@
                    """.trimIndent()
            } else {
                """
                    x$char-@
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 1,
                    expectedRow = 0,
                    expectedDir = Walker.Dir.LEFT,
                    expectedPath = "@-$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['-', 'B', '+', 'x'])
        fun validCharsFromLetter(char: Char) {
            val mapString = if (char == 'x') {
                """
                    |${char}A@
                    """.trimIndent()
            } else {
                """
                    x${char}A@
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 1,
                    expectedRow = 0,
                    expectedDir = Walker.Dir.LEFT,
                    expectedPath = "@A$char",
                    expectedLetters = if (char == 'B') "AB" else "A",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

    @Nested
    inner class Right {

        @ParameterizedTest
        @ValueSource(chars = ['-', 'A', '+', 'x'])
        fun validCharsFromHor(char: Char) {
            val mapString = if (char == 'x') {
                """
                    @-$char|
                    """.trimIndent()
            } else {
                """
                    @-${char}x
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 2,
                    expectedRow = 0,
                    expectedDir = Walker.Dir.RIGHT,
                    expectedPath = "@-$char",
                    expectedLetters = if (char == 'A') "A" else "",
                    expectedIsEnd = char == 'x'
                )
            }
        }

        @ParameterizedTest
        @ValueSource(chars = ['-', 'B', '+', 'x'])
        fun validCharsFromLetter(char: Char) {
            val mapString = if (char == 'x') {
                """
                    @A$char|
                    """.trimIndent()
            } else {
                """
                    @A${char}x
                    """.trimIndent()
            }
            testWalker(mapString) {
                moveToStart()
                nextMove()
                nextMove()
                assertWalker(
                    expectedCol = 2,
                    expectedRow = 0,
                    expectedDir = Walker.Dir.RIGHT,
                    expectedPath = "@A$char",
                    expectedLetters = if (char == 'B') "AB" else "A",
                    expectedIsEnd = char == 'x'
                )
            }
        }

    }

}