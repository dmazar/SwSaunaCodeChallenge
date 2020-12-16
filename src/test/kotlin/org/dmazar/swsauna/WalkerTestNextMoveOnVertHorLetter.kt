package org.dmazar.swsauna

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/** Test [Walker] move when on VERT, HOR, letter or END */
class WalkerTestNextMoveOnVertHorLetter {

    @Nested
    inner class Up {

        @Test
        fun vert() {
            val map = WalkingMap.fromString(" |\n |\n @\nx")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@||", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun cross() {
            val map = WalkingMap.fromString(" +\n |\n @\nx")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@|+", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun letter() {
            val map = WalkingMap.fromString(" A\n |\n @\nx")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@|A", path)
                assertEquals("A", letters)
            }
        }

        @Test
        fun end() {
            val map = WalkingMap.fromString(" x\n |\n @")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@|x", path)
                assertEquals("", letters)
                assertTrue(isEnd())
            }
        }

    }

    @Nested
    inner class Down {

        @Test
        fun vert() {
            val map = WalkingMap.fromString(" @\n |\n |\nx")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(1, col)
                assertEquals(2, row)
                // check path and letters
                assertEquals("@||", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun cross() {
            val map = WalkingMap.fromString(" @\n |\n +\nx")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(1, col)
                assertEquals(2, row)
                // check path and letters
                assertEquals("@|+", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun letter() {
            val map = WalkingMap.fromString(" @\n |\n A\nx")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(1, col)
                assertEquals(2, row)
                // check path and letters
                assertEquals("@|A", path)
                assertEquals("A", letters)
            }
        }

        @Test
        fun end() {
            val map = WalkingMap.fromString(" @\n |\n x")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(1, col)
                assertEquals(2, row)
                // check path and letters
                assertEquals("@|x", path)
                assertEquals("", letters)
                assertTrue(isEnd())
            }
        }

    }

    @Nested
    inner class Left {

        @Test
        fun hor() {
            val map = WalkingMap.fromString(" --@\nx")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@--", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun cross() {
            val map = WalkingMap.fromString(" +-@\nx")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@-+", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun letter() {
            val map = WalkingMap.fromString(" A-@\nx")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@-A", path)
                assertEquals("A", letters)
            }
        }

        @Test
        fun end() {
            val map = WalkingMap.fromString(" x-@")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(1, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@-x", path)
                assertEquals("", letters)
                assertTrue(isEnd())
            }
        }

    }

    @Nested
    inner class Right {

        @Test
        fun hor() {
            val map = WalkingMap.fromString("@--x")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(2, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@--", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun cross() {
            val map = WalkingMap.fromString("@-+x")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(2, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@-+", path)
                assertEquals("", letters)
            }
        }

        @Test
        fun letter() {
            val map = WalkingMap.fromString("@-Ax")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(2, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@-A", path)
                assertEquals("A", letters)
            }
        }

        @Test
        fun end() {
            val map = WalkingMap.fromString("@-x")
            with(Walker(map)) {
                // to start and then 2 moves
                moveToStart()
                nextMove()
                nextMove()
                assertEquals(2, col)
                assertEquals(0, row)
                // check path and letters
                assertEquals("@-x", path)
                assertEquals("", letters)
                assertTrue(isEnd())
            }
        }

    }

}