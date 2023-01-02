enum class Been { START, FINISH, START_AGAIN, FINISH_AGAIN }

fun day24(input: String): Pair<Int, Int> {
    data class Blizzard(val dir: Char, val pos: Pair<Int, Int>)
    data class State(val set: Set<Blizzard>, val pos: Set<Pair<Int, Int>>, val state: Been = Been.START)

    val height = input.split("\n").size - 2
    val width = input.split("\n")[0].length - 2
    val start = Pair(-1, 0)
    val end = Pair(height, width - 1)
    val initState = State(input.split("\n").withIndex().flatMap { row ->
        row.value.withIndex()
            .filter { setOf('>', 'v', '<', '^').contains(it.value) }
            .map { col -> Blizzard(col.value, Pair(row.index - 1, col.index - 1)) }
    }.toSet(), setOf(start))

    tailrec fun countSteps(targetState: Been, state: State = initState, count: Int = 0): Int {
        val pos = state.pos
        val updatedSet = state.set.map {
            Blizzard(
                it.dir, when (it.dir) {
                    '>' -> Pair(it.pos.first, (it.pos.second + 1).mod(width))
                    'v' -> Pair((it.pos.first + 1).mod(height), it.pos.second)
                    '<' -> Pair(it.pos.first, (it.pos.second - 1).mod(width))
                    else -> Pair((it.pos.first - 1).mod(height), it.pos.second)
                }
            )
        }.toSet()
        val cache = updatedSet.associateBy { it.pos }.keys
        val newPoses = pos.flatMap {
            listOf(
                Pair(it.first, it.second),
                Pair(it.first, it.second + 1),
                Pair(it.first + 1, it.second),
                Pair(it.first, it.second - 1),
                Pair(it.first - 1, it.second)
            )
        }.filter {
            !cache.contains(Pair(it.first, it.second)) && it.first in 0 until height && it.second in 0 until width ||
                    it == start ||
                    it == end
        }.toSet()

        val (newPos, newState) = when (state.state) {
            Been.START ->
                if (newPoses.contains(end)) Pair(setOf(end), Been.FINISH) else Pair(newPoses, Been.START)

            Been.FINISH ->
                if (newPoses.contains(start)) Pair(setOf(start), Been.START_AGAIN) else Pair(newPoses, Been.FINISH)

            Been.START_AGAIN ->
                if (newPoses.contains(end)) Pair(setOf(end), Been.FINISH_AGAIN) else Pair(newPoses, Been.START_AGAIN)

            Been.FINISH_AGAIN -> Pair(newPoses, Been.FINISH_AGAIN)
        }

        return if (state.state == targetState) count
        else countSteps(targetState, State(updatedSet, newPos, newState), count + 1)
    }

    return Pair(countSteps(Been.FINISH), countSteps(Been.FINISH_AGAIN))
}
