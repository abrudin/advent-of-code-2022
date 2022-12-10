import kotlin.math.abs
import kotlin.math.sign

fun day9(input: String): Pair<Int, Int> {
    fun tailPositions(count: Int) =
        input.split("\n").runningFold(listOf(List(count) { Pair(0, 0) })) { accInit, instr ->
            (0 until instr.split(" ")[1].toInt()).runningFold(accInit.last()) { acc, _ ->
                when (instr.split(" ")[0]) {
                    "U" -> Pair(0, 1)
                    "R" -> Pair(1, 0)
                    "D" -> Pair(0, -1)
                    else -> Pair(-1, 0)
                }.let {
                    acc.drop(1).runningFold(Pair(acc[0].first + it.first, acc[0].second + it.second)) { head, tail ->
                        if (abs(head.first - tail.first) <= 1 && abs(head.second - tail.second) <= 1) tail
                        else Pair(
                            tail.first + (head.first - tail.first).sign,
                            tail.second + (head.second - tail.second).sign
                        )
                    }
                }
            }
        }.flatten().map { it.last() }.toSet()

    return Pair(tailPositions(2).size, tailPositions(10).size)
}
