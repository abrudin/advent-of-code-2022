fun day1(input: String): Pair<Int, Int> {
    val elvFood = input.split("\n\n").map { it.split("\n").sumOf { it.toInt() } }
    return Pair(elvFood.max(), elvFood.sortedDescending().take(3).sum())
}
