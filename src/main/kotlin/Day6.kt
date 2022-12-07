fun day6(input: String): Pair<Int, Int> {
    fun firstOccurrence(i: Int) = input.windowed(i).withIndex().first { it.value.toSet().size == i }.index + i
    return Pair(firstOccurrence(4), firstOccurrence(14))
}
