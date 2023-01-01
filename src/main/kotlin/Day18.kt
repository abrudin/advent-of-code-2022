fun day18(input: String): Pair<Int, Int> {
    val pixels = input.split("\n").map { it.split(",").map { it.toInt() } }
    val cavities =
        (0..(pixels.maxOf { it[0] } + 1)).flatMap { x ->
            (0..(pixels.maxOf { it[1] } + 1)).flatMap { y ->
                (0..(pixels.maxOf { it[2] } + 1)).map { z ->
                    listOf(x, y, z)
                }
            }
        }.filter { !pixels.contains(it) }

    fun neighbors(it: List<Int>): Set<List<Int>> = setOf(
        listOf(it[0] - 1, it[1], it[2]),
        listOf(it[0] + 1, it[1], it[2]),
        listOf(it[0], it[1] - 1, it[2]),
        listOf(it[0], it[1] + 1, it[2]),
        listOf(it[0], it[1], it[2] - 1),
        listOf(it[0], it[1], it[2] + 1)
    )

    tailrec fun innerClusters(
        allPixels: List<List<Int>>,
        currentIndex: Int = 0,
        allClusters: Set<Set<List<Int>>> = setOf()
    ): List<Set<List<Int>>> {
        return if (currentIndex == allPixels.size)
            allClusters.filter { it.none { it[0] == 0 || it[1] == 0 || it[2] == 0 } }
        else {
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

    fun faces(volumes: Collection<List<Int>>): Int = volumes.sumOf { 6 - neighbors(it).count { volumes.contains(it) } }

    return Pair(faces(pixels), faces(pixels) - innerClusters(cavities).sumOf { innerCavity -> faces(innerCavity) })
}
