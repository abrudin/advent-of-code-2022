fun day4(input: String): Pair<Int, Int> {
    val parsed = input.split("\n").map {
        val (start1, end1, start2, end2) = "(\\d+)-(\\d+),(\\d+)-(\\d+)".toRegex().matchEntire(it)!!.destructured
        Pair(start1.toInt().rangeTo(end1.toInt()), start2.toInt().rangeTo(end2.toInt()))
    }
    return Pair(
        parsed.count { it.first.union(it.second) in setOf(it.first.toSet(), it.second.toSet()) },
        parsed.count { it.first.intersect(it.second).isNotEmpty() }
    )
}
