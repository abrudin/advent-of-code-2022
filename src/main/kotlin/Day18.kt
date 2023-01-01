fun day18(input: String): Pair<Int, Int> {
    val parsed = input.split("\n").map { it.split(",").map { it.toInt() } }
    fun neighbors(it: List<Int>): Set<List<Int>> = setOf(
        listOf(it[0] - 1, it[1], it[2]),
        listOf(it[0] + 1, it[1], it[2]),
        listOf(it[0], it[1] - 1, it[2]),
        listOf(it[0], it[1] + 1, it[2]),
        listOf(it[0], it[1], it[2] - 1),
        listOf(it[0], it[1], it[2] + 1)
    )

    val cavities =
        (0..(parsed.maxOf { it[0] } + 1)).flatMap { x ->
            (0..(parsed.maxOf { it[1] } + 1)).flatMap { y ->
                (0..(parsed.maxOf { it[2] } + 1)).map {
                    listOf(x, y, it)
                }
            }
        }.filter { !parsed.contains(it) }

    tailrec fun innerClusters(
        allPixels: List<List<Int>>,
        currentIndex: Int = 0,
        allClusters: Set<Set<List<Int>>> = setOf()
    ): List<Set<List<Int>>> {
        return if (currentIndex == allPixels.size) allClusters.filter { it.none { it[0] == 0 || it[1] == 0 || it[2] == 0 } } else {
            val current = allPixels[currentIndex]
            val neighbors = neighbors(current).filter { cavities.contains(it) }
            val (withoutNeighbors, withNeighbors) = allClusters
                .partition { cluster -> neighbors.none { cluster.contains(it) } }
            innerClusters(
                allPixels,
                currentIndex + 1,
                withoutNeighbors.toSet() + setOf(withNeighbors.fold(setOf(current)) { a, b -> a + b })
            )
        }
    }

    fun faces(pixels: Collection<List<Int>>): Int = pixels.sumOf { 6 - neighbors(it).count { pixels.contains(it) } }

    return Pair(faces(parsed), faces(parsed) - innerClusters(cavities).sumOf { innerCavity -> faces(innerCavity) })
}
