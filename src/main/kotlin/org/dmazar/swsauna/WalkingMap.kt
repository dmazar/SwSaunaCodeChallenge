package org.dmazar.swsauna

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

    /** @return true if char at given position is START */
    fun isStart(col: Int, row: Int) = map[row][col] == C_START

    /** @return true if char at given position is END */
    fun isEnd(col: Int, row: Int) = map[row][col] == C_END

    /** @return true if char at given position is VERT */
    fun isVert(col: Int, row: Int) = map[row][col] == C_VERT

    /** @return true if char at given position is HOR */
    fun isHor(col: Int, row: Int) = map[row][col] == C_HOR

    /** @return true if char at given position is CROSS */
    fun isCross(col: Int, row: Int) = map[row][col] == C_CROSS

}