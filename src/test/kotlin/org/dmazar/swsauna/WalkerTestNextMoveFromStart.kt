package org.dmazar.swsauna

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/** Test [Walker] move from start */
class WalkerTestNextMoveFromStart {

    @Test
    fun `up VERT`() {
        val map = WalkingMap.fromString(" |\n @\nx")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(0, row)
            // check path and letters
            Assertions.assertEquals("@|", path)
            Assertions.assertEquals("", letters)
        }
    }

    @Test
    fun `up CROSS`() {
        val map = WalkingMap.fromString(" +\n @\nx")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(0, row)
            // check path and letters
            Assertions.assertEquals("@+", path)
            Assertions.assertEquals("", letters)
        }
    }

    @Test
    fun `up letter`() {
        val map = WalkingMap.fromString(" A\n @\nx")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(0, row)
            // check path and letters
            Assertions.assertEquals("@A", path)
            Assertions.assertEquals("A", letters)
        }
    }

    @Test
    fun `up END`() {
        val map = WalkingMap.fromString(" x\n @")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(0, row)
            // check path and letters
            Assertions.assertEquals("@x", path)
            Assertions.assertEquals("", letters)
            Assertions.assertTrue(isEnd())
        }
    }

    @Test
    fun `down VERT`() {
        val map = WalkingMap.fromString(" @\n |\nx")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(1, row)
            // check path and letters
            Assertions.assertEquals("@|", path)
            Assertions.assertEquals("", letters)
        }
    }

    @Test
    fun `down CROSS`() {
        val map = WalkingMap.fromString(" @\n +\nx")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(1, row)
            // check path and letters
            Assertions.assertEquals("@+", path)
            Assertions.assertEquals("", letters)
        }
    }

    @Test
    fun `down letter`() {
        val map = WalkingMap.fromString(" @\n A\nx")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(1, row)
            // check path and letters
            Assertions.assertEquals("@A", path)
            Assertions.assertEquals("A", letters)
        }
    }

    @Test
    fun `down END`() {
        val map = WalkingMap.fromString(" @\n x")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(1, row)
            // check path and letters
            Assertions.assertEquals("@x", path)
            Assertions.assertEquals("", letters)
            Assertions.assertTrue(isEnd())
        }
    }

    @Test
    fun `left HOR`() {
        val map = WalkingMap.fromString(" -@\nx")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(0, row)
            // check path and letters
            Assertions.assertEquals("@-", path)
            Assertions.assertEquals("", letters)
        }
    }

    @Test
    fun `left CROSS`() {
        val map = WalkingMap.fromString(" +@\nx")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(0, row)
            // check path and letters
            Assertions.assertEquals("@+", path)
            Assertions.assertEquals("", letters)
        }
    }

    @Test
    fun `left letter`() {
        val map = WalkingMap.fromString(" A@\nx")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(0, row)
            // check path and letters
            Assertions.assertEquals("@A", path)
            Assertions.assertEquals("A", letters)
        }
    }

    @Test
    fun `left END`() {
        val map = WalkingMap.fromString(" x@")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(0, row)
            // check path and letters
            Assertions.assertEquals("@x", path)
            Assertions.assertEquals("", letters)
            Assertions.assertTrue(isEnd())
        }
    }

    @Test
    fun `right HOR`() {
        val map = WalkingMap.fromString("@-\n x")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(0, row)
            // check path and letters
            Assertions.assertEquals("@-", path)
            Assertions.assertEquals("", letters)
        }
    }

    @Test
    fun `right CROSS`() {
        val map = WalkingMap.fromString("@+\n x")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(0, row)
            // check path and letters
            Assertions.assertEquals("@+", path)
            Assertions.assertEquals("", letters)
        }
    }

    @Test
    fun `right letter`() {
        val map = WalkingMap.fromString("@A\n x")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(1, col)
            Assertions.assertEquals(0, row)
            // check path and letters
            Assertions.assertEquals("@A", path)
            Assertions.assertEquals("A", letters)
        }
    }

    @Test
    fun `right END`() {
        val map = WalkingMap.fromString(" @x")
        with(Walker(map)) {
            moveToStart()
            nextMove()
            Assertions.assertEquals(2, col)
            Assertions.assertEquals(0, row)
            // check path and letters
            Assertions.assertEquals("@x", path)
            Assertions.assertEquals("", letters)
            Assertions.assertTrue(isEnd())
        }
    }

    @Test
    fun `no path`() {
        val map = WalkingMap.fromString("@\n\nx")
        with(Walker(map)) {
            moveToStart()
            Assertions.assertThrows(RuntimeException::class.java) {
                nextMove()
            }
        }
    }

    @Test
    fun `multiple paths`() {
        val map = WalkingMap.fromString("-@-\n\nx")
        with(Walker(map)) {
            moveToStart()
            Assertions.assertThrows(RuntimeException::class.java) {
                nextMove()
            }
        }
    }

}