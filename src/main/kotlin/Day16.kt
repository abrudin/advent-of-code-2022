fun day16(input: String): Pair<Int, Int> {
    val layout = input.split("\n").associate {
        val (valve, flowRate, connections) = "Valve ([A-Z]+) has flow rate=(\\d+); tunnels? leads? to valves? (.*)".toRegex()
            .matchEntire(it)!!.destructured
        Pair(valve, Pair(connections.split(", "), flowRate.toInt()))
    }

    data class SimplePath(val room: String, val openedValves: Set<String>)

    /*
     * Strategy:
     *   Keep a state with
     *     1. Which room you are in
     *     2. Which valves are open??
     *     3. Which total pressure has been released
     *   1. Start in room AA with 0 total pressure released
     *   2. Fork out the following options:
     *     2a. If the valve in this room is not open, open it.
     *     2b. Go into each of this room's neighbors
     *   3. Filter away, comparing state history with options that
     *        * lands in the same room
     *        * AND has the same valves open
     *        * AND has less or equal pressure
     */

    val minPath = (1..30).fold(mapOf(Pair("AA", Pair(setOf(), 0)))) { paths: Map<String, Pair<Set<String>, Int>>, i ->
        println(i)
        paths.entries.fold(mapOf()) { acc: Map<String, Pair<Set<String>, Int>>, path ->
            val lastRoom = layout[path.key] ?: throw Exception("Unknown room")
            val adjacentRooms = lastRoom.first
            val pressureHere = lastRoom.second
            val nextRooms = adjacentRooms.map { Pair(it, Pair(path.value.first, path.value.second)) } +
                    if (!path.value.first.contains(path.key) && pressureHere != 0) {
                        setOf(
                            Pair(
                                path.key, Pair(path.value.first + path.key, path.value.second + pressureHere * (30 - i))
                            )
                        )
                    } else setOf()
            acc + nextRooms.filter { acc[it.first]?.let { a -> it.second.second > a.second } ?: true }
        }
    }
    println(minPath.maxBy { it.value.second })
    // val result = pressure(minPath, layout, 30)
    return Pair(minPath.maxOf { it.value.second }, 0)
}

fun pressure(visitingOrder: List<String>, layout: Map<String, Pair<List<String>, Int>>, minutes: Int) =
    visitingOrder.withIndex().fold(Pair("AA", 0)) { acc, i ->
        if (acc.first == i.value) Pair(i.value, acc.second + (layout[i.value]?.second ?: 0) * (minutes - i.index))
        else Pair(i.value, acc.second)
    }.second


fun printGraph(layout: Map<String, Pair<List<String>, Int>>): String {
    val edges = layout.entries
        .flatMap { it.value.first.map { dest -> "${it.key} -> $dest" } }
        .joinToString("\n  ")

    return """
           |digraph G {
           |  concentrate=true
           |  rankdir="LR"
           |  ${layout.entries.joinToString("\n  ") { "${it.key} [label = \"${it.key}:${it.value.second}\"]" }}
           |  $edges
           |}
           """.trimMargin()

}
