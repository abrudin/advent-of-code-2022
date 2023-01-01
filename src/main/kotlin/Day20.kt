fun day20(input: String): Pair<Long, Long> {
    val original = input.split("\n").map { it.toLong() }
    fun <T> removeAt(original: List<T>, index: Int): Pair<List<T>, T> =
        Pair(original.subList(0, index) + original.subList(index + 1, original.size), original[index])

    fun <T> insertAt(original: List<T>, index: Int, value: T): List<T> =
        original.subList(0, index) + value + original.subList(index, original.size)

    fun mix(original: List<Long>, numberOfTimes: Int = 1): List<Long> =
        (1..numberOfTimes).fold(original.withIndex().toList()) { last, _ ->
            last.indices.fold(last) { acc, i ->
                val listValue = original[i]
                val pos = acc.indexOfFirst { it.index == i }
                val targetPos = ((pos + listValue).mod(original.size - 1))
                val afterRemoval = removeAt(acc, pos)
                insertAt(afterRemoval.first, targetPos, afterRemoval.second)
            }
        }.map { it.value }

    fun groveCoordinates(list: List<Long>) =
        listOf(1000, 2000, 3000).sumOf { list[(list.indexOf(0) + it).mod(list.size)] }
    return Pair(
        groveCoordinates(mix(original)),
        groveCoordinates(mix(original.map { it * 811589153 }, 10))
    )
}