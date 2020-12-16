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

    /** Direction */
    private enum class Dir {
        UNKNOWN, LEFT, RIGHT, UP, DOWN
    }

    /** Current moving direction */
    private var dir = Dir.UNKNOWN

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
                    makeValidMove(c, r, Dir.UNKNOWN)
                    return
                }
            }
        }
    }

    /** Moves to next position */
    fun nextMove() {
        if (isEnd()) {
            return
        } else if (map.isStart(col, row)) {
            nextMoveFromStart()
        } else if (map.isVert(col, row) || map.isHor(col, row) || map.isLetter(col, row)) {
            nextMoveVertHorLetter()
        }
    }

    /** Moves to specified position (no check, assumes it is valid) and updates [path] and [letters] */
    private fun makeValidMove(newCol: Int, newRow: Int, newDir: Dir) {
        // move to new position
        col = newCol
        row = newRow
        dir = newDir
        val currentChar = map.map[newRow][newCol]
        // add char to path
        _path += currentChar
        // collect letter if does not exists
        if (map.isLetter(col, row) && !_letters.contains(currentChar)) {
            _letters += currentChar
        }
    }

    private fun nextMoveFromStart() {
        // check for VERT, HOR, CROSS, END or letter around
        // check for error if no path or multiple paths are possible

        // check for possible paths
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

        // count paths and throw error if != 1
        val numPaths = listOf(canUp, canDown, canLeft, canRight).filter { it }.count()
        when {
            numPaths == 0 -> throw RuntimeException("no path")
            numPaths > 1 -> throw RuntimeException("multiple paths from $col, $row: $numPaths")
        }

        // make a move
        when {
            // up
            canUp -> makeValidMove(col, row - 1, Dir.UP)
            // down
            canDown -> makeValidMove(col, row + 1, Dir.DOWN)
            // left
            canLeft -> makeValidMove(col - 1, row, Dir.LEFT)
            // right
            canRight -> makeValidMove(col + 1, row, Dir.RIGHT)
        }
    }

    private fun nextMoveVertHorLetter() {
        // keep the same direction
        // check for valid next char
        // ignore other chars around

        // determine relative move from direction
        var deltaCol = 0
        var deltaRow = 0
        when(dir) {
            Dir.UNKNOWN -> throw RuntimeException("invalid direction UNKNOWN on $row, $col")
            Dir.UP -> deltaRow = -1
            Dir.DOWN -> deltaRow = 1
            Dir.LEFT -> deltaCol = -1
            Dir.RIGHT -> deltaCol = 1
        }

        // make a move or error
        if (dir == Dir.UP || dir == Dir.DOWN) {
            // valid chars up are VERT, LETTER, CROSS, END
            if (map.isVert(col + deltaCol, row + deltaRow)
                || map.isLetter(col + deltaCol, row + deltaRow)
                || map.isCross(col + deltaCol, row + deltaRow)
                || map.isEnd(col + deltaCol, row + deltaRow)
            ) {
                // move to next char, keep the direction
                makeValidMove(col + deltaCol, row + deltaRow, dir)
            } else {
                throw RuntimeException("invalid char UP from $row, $col")
            }
        } else {
            // valid chars up are HOR, LETTER, CROSS, END
            if (map.isHor(col + deltaCol, row + deltaRow)
                || map.isLetter(col + deltaCol, row + deltaRow)
                || map.isCross(col + deltaCol, row + deltaRow)
                || map.isEnd(col + deltaCol, row + deltaRow)
            ) {
                // move to next char, keep the direction
                makeValidMove(col + deltaCol, row + deltaRow, dir)
            } else {
                throw RuntimeException("invalid char UP from $row, $col")
            }
        }
    }

}