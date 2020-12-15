package org.dmazar.swsauna

class Walker(private val map: WalkingMap) {

    var col: Int = 0
        private set
    var row: Int = 0
        private set

    /** Moves to START, runs the path to the END and collects path results */
    fun findAndRunThePath() {
        moveToStart()
    }

    /** Moves position to START */
    fun moveToStart() {
        for (r in 0 until map.numRows) {
            for (c in 0 until map.numCols[r]) {
                if (map.isStart(c, r)) {
                    col = c
                    row = r
                    return
                }
            }
        }
    }
}