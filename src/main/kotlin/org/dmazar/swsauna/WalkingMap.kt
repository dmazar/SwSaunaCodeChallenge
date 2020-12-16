package org.dmazar.swsauna

import java.lang.Exception

/** Map of characters with walking path */
class WalkingMap private constructor(val map: Array<String>) {

    companion object {

        private const val C_START = '@'
        private const val C_END = 'x'
        private const val C_VERT = '|'
        private const val C_HOR = '-'
        private const val C_CROSS = '+'

        /** Creates map from map represented as String with lines delimited with new line char */
        fun fromString(mapAsString: String): WalkingMap {
            // check for errors
            if (mapAsString.isEmpty()) {
                throw RuntimeException("empty map")
            }
            if (!mapAsString.contains(C_START)) {
                throw RuntimeException("no start $C_START")
            }
            if (mapAsString.count { it == C_START } > 1) {
                throw RuntimeException("multiple start $C_START")
            }
            if (!mapAsString.contains(C_END)) {
                throw RuntimeException("no end $C_END")
            }
            if (mapAsString.count { it == C_END } > 1) {
                throw RuntimeException("multiple end $C_END")
            }

            // split to lines
            return WalkingMap(mapAsString.lines().toTypedArray())
        }
    }

    /** Number of rows in the map */
    val numRows = map.size

    /** Array with row lengths */
    val numCols = IntArray(numRows) { row -> map[row].length }

    /** @return true if position is valid and char at given position is START */
    fun isStart(col: Int, row: Int) = isChar(col, row, C_START)

    /** @return true if position is valid and char at given position is END */
    fun isEnd(col: Int, row: Int) = isChar(col, row, C_END)

    /** @return true if position is valid and char at given position is VERT */
    fun isVert(col: Int, row: Int) = isChar(col, row, C_VERT)

    /** @return true if position is valid and char at given position is HOR */
    fun isHor(col: Int, row: Int) = isChar(col, row, C_HOR)

    /** @return true if position is valid and char at given position is CROSS */
    fun isCross(col: Int, row: Int) = isChar(col, row, C_CROSS)

    /** @return true if position is valid and char at given position is [c] */
    private fun isChar(col: Int, row: Int, c: Char) = try {
        map[row][col] == c
    } catch (e: Exception) {
        false
    }

    /** @return true if position is valid and char is letter */
    fun isLetter(col: Int, row: Int) = try {
        map[row][col].isLetter() && !isEnd(col, row)
    } catch (e: Exception) {
        false
    }

}