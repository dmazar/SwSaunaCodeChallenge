package org.dmazar.swsauna

class Walker(private val map: WalkingMap) {

    /** Position on the map: current column */
    var col: Int = 0
        private set
    /** Position on the map: current row */
    var row: Int = 0
        private set

    private val _path = mutableListOf<Char>()
    /** Collected full path chars */
    val path: String get() = _path.joinToString("")

    private val _letters = mutableListOf<Char>()
    /** Collected letters */
    val letters: String get() = _letters.joinToString("")

    /** Moves to START, runs the path to the END and collects path results */
    fun findAndRunThePath() {
        moveToStart()
    }

    /** @return true if reached the END of the path */
    fun isEnd() = map.isEnd(col, row)

    /** Moves position to START */
    fun moveToStart() {
        for (r in 0 until map.numRows) {
            for (c in 0 until map.numCols[r]) {
                if (map.isStart(c, r)) {
                    makeValidMove(c, r)
                    return
                }
            }
        }
    }

    /** Moves to next position */
    fun nextMove() {
        if (map.isStart(col, row)) {
            nextMoveFromStart()
        }
    }

    /** Moves to specified position (no check, assumes it is valid) and updates [path] and [letters] */
    private fun makeValidMove(newCol: Int, newRow: Int) {
        // move to new position
        col = newCol
        row = newRow
        val currentChar = map.map[newRow][newCol]
        // add char to path
        _path += currentChar
        // collect letter if does not exists
        if (map.isLetter(col, row) && !_letters.contains(currentChar)) {
            _letters += currentChar
        }
    }

    private fun nextMoveFromStart() {
        // check for VERT, HOR, CROSS or letter around
        // note: not checking for error if multiple paths are possible
        val canUp = map.isVert(col, row - 1)
                || map.isCross(col, row - 1)
                || map.isEnd(col, row - 1)
                || map.isLetter(col, row - 1)
        val canDown = map.isVert(col, row + 1)
                || map.isCross(col, row + 1)
                || map.isEnd(col, row + 1)
                || map.isLetter(col, row + 1)
        val canLeft = map.isHor(col - 1, row)
                || map.isCross(col - 1, row)
                || map.isEnd(col - 1, row)
                || map.isLetter(col - 1, row)
        val canRight = map.isHor(col + 1, row)
                || map.isCross(col + 1, row)
                || map.isEnd(col + 1, row)
                || map.isLetter(col + 1, row)

        // count possible paths and throw error if != 1
        val numPaths = listOf(canUp, canDown, canLeft, canRight).filter { it }.count()
        when {
            numPaths == 0 -> throw RuntimeException("no path")
            numPaths > 1 -> throw RuntimeException("multiple paths from $col, $row: $numPaths")
        }

        when {
            // up
            canUp -> makeValidMove(col, row - 1)
            // down
            canDown -> makeValidMove(col, row + 1)
            // left
            canLeft -> makeValidMove(col - 1, row)
            // right
            canRight -> makeValidMove(col + 1, row)
            else -> throw RuntimeException("can not move from $row, $col")
        }
    }
}