fun day12(input: String): Pair<Int, Int> {
    val tiles = input.filter { it != '\n' }
    val cols = input.indexOf('\n')
    val rows = tiles.length / cols
    val mapped = tiles.map { if (it == 'S') 'a' else if (it == 'E') 'z' else it }

    data class Hill(val previous: Set<Int>, val current: Set<Int>, val steps: Int = 0)

    fun stepsUntilEnd(init: Set<Int>): Int = generateSequence(Hill(setOf(), init)) { hill ->
        Hill(hill.previous + hill.current, hill.current.flatMap { i ->
            listOfNotNull(
                if (i >= cols) i - cols else null, // Up
                if (i < (rows - 1) * cols) i + cols else null, // Down
                if (i % cols > 0) i - 1 else null, // Left
                if (i % cols < (cols - 1)) i + 1 else null // Right
            ).filter { !hill.previous.contains(it) && mapped[it] <= mapped[i].inc() }
        }.toSet(), hill.steps.inc())
    }.dropWhile { !it.current.contains(tiles.indexOf('E')) }.first().steps

    return Pair(
        stepsUntilEnd(setOf(tiles.indexOf('S'))),
        stepsUntilEnd(mapped.withIndex().filter { it.value == 'a' }.map { it.index }.toSet())
    )
}
