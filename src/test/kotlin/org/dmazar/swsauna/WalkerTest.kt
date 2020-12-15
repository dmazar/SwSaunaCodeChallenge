package org.dmazar.swsauna

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

/** Test [Walker] */
internal class WalkerTest {

    @Test
    fun moveToStart() {
        var map = WalkingMap.fromString("""
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
        }

        map = WalkingMap.fromString("""
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
        }
    }
}