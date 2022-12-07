fun day3(input: String): Pair<Int, Int> {
    val rucksack = input.split("\n").map { it.map { if (it < 'a') it - 'A' + 27 else it - 'a' + 1 } }

    return Pair(
        rucksack.sumOf { it.take(it.size / 2).toSet().intersect(it.drop(it.size / 2).toSet()).first() },
        rucksack.map { it.toSet() }.chunked(3).sumOf { it.reduce { a, b -> a.intersect(b) }.first() }
    )
}
