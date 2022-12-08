import AocUtils.takeWhileInclusive

fun day8(input: String): Pair<Int, Int> {
    val trees = input.filter { it != '\n' }
    val rows = input.indexOf('\n')
    val cols = trees.length / rows
    fun row(i: Int) = i / rows
    fun col(i: Int) = i % cols
    fun indexOf(row: Int, col: Int) = row * rows + col
    fun treesInLine(i: Int): Set<List<Char>> =
        setOf(
            (0 until row(i)).reversed().map { trees[indexOf(it, col(i))] },
            (0 until col(i)).reversed().map { trees[indexOf(row(i), it)] },
            (row(i).inc() until rows).map { trees[indexOf(it, col(i))] },
            (col(i).inc() until cols).map { trees[indexOf(row(i), it)] }
        )
    return Pair(
        trees.withIndex().count { (index, value) -> treesInLine(index).any { it.all { it < value } } },
        trees.withIndex().maxOf { (index, value) ->
            treesInLine(index).map { it.takeWhileInclusive { it < value }.size }.fold(1) { a, b -> a * b }
        })
}
