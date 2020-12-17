package org.dmazar.swsauna

import org.junit.jupiter.api.Assertions.*

/** Helper for testing [Walker] scenario: creates map and walker and executes given [testBody] on it */
fun testWalker(mapString: String, testBody: Walker.()->Unit) {
    val map = WalkingMap.fromString(mapString)
    val walker = Walker(map)
    walker.testBody()
}

/** Asserts that walker meets expected values */
fun Walker.assertWalker(
    expectedCol: Int,
    expectedRow: Int,
    expectedDir: Walker.Dir,
    expectedPath: String,
    expectedLetters: String,
    expectedIsEnd: Boolean,
) {
    assertEquals(expectedCol, col)
    assertEquals(expectedRow, row)
    assertEquals(expectedDir, dir)
    assertEquals(expectedPath, path)
    assertEquals(expectedLetters, letters)
    assertEquals(expectedIsEnd, isEnd())
}