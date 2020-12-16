package org.dmazar.swsauna

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/** Test [Walker] move from start */
class WalkerTestNextMoveFromStart {

    @Nested
    inner class Up {

        @Test
        fun vert() {
            val map = WalkingMap.fromString(" |\n @\nx")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@|", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun cross() {
            val map = WalkingMap.fromString(" +\n @\nx")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@+", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun letter() {
            val map = WalkingMap.fromString(" A\n @\nx")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@A", path)
                assertEquals("A", letters)
            }
        }

        @Test
        fun end() {
            val map = WalkingMap.fromString(" x\n @")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@x", path)
                assertEquals("", letters)
                assertTrue(isEnd())
            }
        }

    }

    @Nested
    inner class Down {

        @Test
        fun vert() {
            val map = WalkingMap.fromString(" @\n |\nx")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(1, row)
                // check path and letters
                assertEquals("@|", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun cross() {
            val map = WalkingMap.fromString(" @\n +\nx")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(1, row)
                // check path and letters
                assertEquals("@+", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun letter() {
            val map = WalkingMap.fromString(" @\n A\nx")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(1, row)
                // check path and letters
                assertEquals("@A", path)
                assertEquals("A", letters)
            }
        }

        @Test
        fun end() {
            val map = WalkingMap.fromString(" @\n x")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(1, row)
                // check path and letters
                assertEquals("@x", path)
                assertEquals("", letters)
                assertTrue(isEnd())
            }
        }

    }

    @Nested
    inner class Left {

        @Test
        fun hor() {
            val map = WalkingMap.fromString(" -@\nx")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@-", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun cross() {
            val map = WalkingMap.fromString(" +@\nx")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@+", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun letter() {
            val map = WalkingMap.fromString(" A@\nx")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@A", path)
                assertEquals("A", letters)
            }
        }

        @Test
        fun end() {
            val map = WalkingMap.fromString(" x@")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@x", path)
                assertEquals("", letters)
                assertTrue(isEnd())
            }
        }

    }

    @Nested
    inner class Right {

        @Test
        fun hor() {
            val map = WalkingMap.fromString("@-\n x")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@-", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun cross() {
            val map = WalkingMap.fromString("@+\n x")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@+", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun letter() {
            val map = WalkingMap.fromString("@A\n x")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@A", path)
                assertEquals("A", letters)
            }
        }

        @Test
        fun end() {
            val map = WalkingMap.fromString(" @x")
            with(Walker(map)) {
                moveToStart()
                nextMove()
                assertEquals(2, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@x", path)
                assertEquals("", letters)
                assertTrue(isEnd())
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