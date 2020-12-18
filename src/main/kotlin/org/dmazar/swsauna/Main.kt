package org.dmazar.swsauna

/** Main program entry for finding the path */
fun main() {
    // define the map here
    val mapString = """
        @---A---+
                |
        x-B-+   C
            |   |
            +---+
        """.trimIndent()

    // create map and walker for that map
    val walker = Walker(WalkingMap.fromString(mapString))

    // run the walker through the path
    walker.findAndRunThePath()

    // print the result
    println("Path: ${walker.path}")
    println("Letters: ${walker.letters}")
}