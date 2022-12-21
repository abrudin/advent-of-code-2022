import java.lang.Integer.max
import java.lang.Integer.min

fun day14(input: String): Pair<Int, Int> {
    fun asPair(s: String): Pair<Int, Int> = Pair(s.split(",")[0].toInt(), s.split(",")[1].toInt())
    fun expand(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Set<Pair<Int, Int>> =
        (min(p1.first, p2.first)..max(p1.first, p2.first)).flatMap { first ->
            (min(p1.second, p2.second)..max(p1.second, p2.second)).map {
                Pair(first, it)
            }
        }.toSet()

    val ranges = input.split("\n").fold(setOf<Pair<Int, Int>>()) { acc, it ->
        acc + it.split(" -> ").windowed(2).flatMap { expand(asPair(it[0]), asPair(it[1])) }
    }

    val maxY = ranges.maxOf { it.second }

    fun sandFall(withFloor: Boolean): Sequence<Set<Pair<Int, Int>>> =
        generateSequence(setOf()) {
            generateSequence(Pair(it, Pair(500, 0))) {
                val down = Pair(it.second.first, it.second.second + 1)
                val left = Pair(it.second.first - 1, it.second.second + 1)
                val right = Pair(it.second.first + 1, it.second.second + 1)
                if (it.second.second == maxY + 1)
                    if (withFloor) Pair(it.first + it.second, Pair(500, -1))
                    else Pair(it.first, Pair(500, -1))
                else if (!it.first.contains(down) && !ranges.contains(down)) Pair(it.first, down)
                else if (!it.first.contains(left) && !ranges.contains(left)) Pair(it.first, left)
                else if (!it.first.contains(right) && !ranges.contains(right)) Pair(it.first, right)
                else Pair(it.first + it.second, Pair(500, -1))
            }.dropWhile { it.second != Pair(500, -1) }.first().first
        }

    return Pair(
        sandFall(false).windowed(2).dropWhile { it[0] != it[1] }.first().first().size,
        sandFall(true).dropWhile { !it.contains(Pair(500, 0)) }.first().size
    )
}
