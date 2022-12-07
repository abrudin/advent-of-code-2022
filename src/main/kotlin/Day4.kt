fun day4(input: String): Pair<Int, Int> {
    val parsed = input.split("\n").map {
        listOf(Pair(0, 0), Pair(0, 1), Pair(1, 0), Pair(1, 1)).map { p ->
            it.split(",")[p.first].split("-")[p.second].toInt()
        }
    }
    return Pair(
        parsed.count { (it[0]..it[1]).union((it[2]..it[3])) in setOf((it[0]..it[1]).toSet(), (it[2]..it[3]).toSet()) },
        parsed.count { (it[0]..it[1]).intersect(it[2]..it[3]).isNotEmpty() }
    )
}

// Alternatively
// it[0] <= it[2] && it[1] >= it[3] || it[2] <= it[0] && it[3] >= it[1]
// it[0] <= it[2] && it[1] >= it[2] || it[2] <= it[0] && it[3] >= it[0]

