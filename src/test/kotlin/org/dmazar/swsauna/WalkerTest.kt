package org.dmazar.swsauna

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/** Test [Walker] */
internal class WalkerTest {

    @Test
    fun moveToStart() {
        var map = WalkingMap.fromString(
            """
                -
                      @-A
                x-B-+   C
                    |   |
                    +---+
            """.trimIndent()
        )
        with(Walker(map)) {
            moveToStart()
            assertEquals(6, col)
            assertEquals(1, row)
            // check path and letters
            assertEquals("@", path)
            assertEquals("", letters)
        }

        map = WalkingMap.fromString(
            """
                       -A
                x-B-+   C
                    |   |
                    @ --+
            """.trimIndent()
        )
        with(Walker(map)) {
            moveToStart()
            assertEquals(4, col)
            assertEquals(3, row)
            // check path and letters
            assertEquals("@", path)
            assertEquals("", letters)
        }
    }

    @Test
    fun isEnd() {
        val map = WalkingMap.fromString("@\nx")
        with(Walker(map)) {
            moveToStart()
            assertFalse(isEnd())
            nextMove()
            assertTrue(isEnd())
            // check path and letters
            assertEquals("@x", path)
            assertEquals("", letters)

            // next move does not happen
            nextMove()
            assertTrue(isEnd())
            assertEquals("@x", path)
            assertEquals("", letters)
        }
    }

}