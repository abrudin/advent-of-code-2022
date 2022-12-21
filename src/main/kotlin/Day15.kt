import kotlin.math.abs

fun day15(input: String, target: Int): Pair<Int, Long> {
    fun md(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Int = abs(p1.first - p2.first) + abs(p1.second - p2.second)
    fun xStartToEnd(p: Pair<Int, Int>, distance: Int, rowNo: Int): Pair<Int, Int>? {
        val distanceLeft = distance - abs(rowNo - p.second)
        return if (distanceLeft >= 0) Pair(p.first - distanceLeft, p.first + distanceLeft) else null
    }

    val sensorsAndBeacons = input.split("\n").map {
        val (sx, sy, bx, by) = "Sensor at x=([-\\d]+), y=([-\\d]+): closest beacon is at x=([-\\d]+), y=([-\\d]+)".toRegex()
            .matchEntire(it)!!.destructured
        Pair(Pair(sx.toInt(), sy.toInt()), Pair(bx.toInt(), by.toInt()))
    }
    val beacons = sensorsAndBeacons.map { it.second }.toSet()

    fun inRow(rowNo: Int): List<Pair<Int, Int>> =
        sensorsAndBeacons.mapNotNull { xStartToEnd(it.first, md(it.first, it.second), rowNo) }
            .sortedBy { it.first }.fold(listOf()) { acc, it ->
                if (acc.isEmpty()) listOf(it)
                else if (it.first <= acc.last().second + 1)
                    if (it.second <= acc.last().second) acc
                    else acc.dropLast(1) + Pair(acc.last().first, it.second)
                else acc + it
            }

    val b = generateSequence(0) { it + 1 }.map { Pair(it, inRow(it)) }.first { it.second.size > 1 }

    return Pair(
        inRow(target).sumOf { it.second - it.first } + 1 - beacons.filter { it.second == target }.map { it.first }.size,
        (b.second.first().second + 1).toLong() * 4000000 + b.first
    )
}
