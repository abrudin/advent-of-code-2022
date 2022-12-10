import kotlin.math.abs

fun day10(input: String): Pair<Int, String> {
    val result = input.split("\n")
        .map { it.split(" ").let { Pair(it[0], it.getOrElse(1) { "0" }.toInt()) } }
        .runningFold(listOf(1)) { acc, instr ->
            when (instr.first) {
                "addx" -> listOf(acc.last(), acc.last() + instr.second)
                else -> listOf(acc.last())
            }
        }.flatten()
    return Pair(
        result.withIndex().drop(19).chunked(40).sumOf { it[0].value * (it[0].index + 1) },
        result.mapIndexed { index, value -> if (abs(index % 40 - value) <= 1) '█' else '.' }
            .chunked(40).dropLast(1).joinToString("\n") { it.joinToString("") }
    )
}
