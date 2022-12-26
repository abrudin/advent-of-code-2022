import java.lang.Integer.min

data class Path(val currentRoom: String, val turnsLeft: Int)
data class Room(val name: String, val flowRate: Int, val connections: List<String>)
data class RoomInfo(val distance: Int, val flowRate: Int)
data class ValveLeft(val path: Path, val nextRoom: String = path.currentRoom, val roomInfo: RoomInfo = RoomInfo(0, 0))

fun day16(input: String): Pair<Int, Int> {
    val caves = distancesToPressureCaves(input.split("\n").map {
        val (valve, flowRate, connections) = "Valve ([A-Z]+) has flow rate=(\\d+); tunnels? leads? to valves? (.*)".toRegex()
            .matchEntire(it)!!.destructured
        Room(valve, flowRate.toInt(), connections.split(", "))
    }.associateBy { it.name })

    return Pair(best(listOf(Path("AA", 30)), 0, caves), best(listOf(Path("AA", 26), Path("AA", 26)), 0, caves))
}

fun <T> combinations(listOfLists: List<List<T>>): List<List<T>> =
    if (listOfLists.isEmpty()) emptyList()
    else if (listOfLists.size == 1) listOfLists[0].map { listOf(it) }
    else listOfLists[0].flatMap { i -> combinations(listOfLists.drop(1)).map { it + i } }

fun best(paths: List<Path>, totalPressureReleased: Int, distances: Map<String, Map<String, RoomInfo>>): Int =
    paths.map { path ->
        (distances[path.currentRoom] ?: mapOf()).filter { it.value.distance < path.turnsLeft }
            .map { ValveLeft(path, it.key, it.value) }
    }.let { valvesLeft ->
        if (valvesLeft.all { it.isEmpty() }) totalPressureReleased
        else combinations(valvesLeft.map { it.ifEmpty { listOf(ValveLeft(Path("", 0))) } })
            .map { it.distinctBy { it.nextRoom } }
            .maxOf { newPaths ->
                best(
                    newPaths.map { Path(it.nextRoom, it.path.turnsLeft - it.roomInfo.distance) },
                    newPaths.fold(totalPressureReleased) { acc, it -> acc + it.roomInfo.flowRate * (it.path.turnsLeft - it.roomInfo.distance) },
                    newPaths.fold(distances) { acc, i -> acc.mapValues { it.value - i.path.currentRoom - i.nextRoom } })
            }
    }

fun distancesToPressureCaves(graph: Map<String, Room>): Map<String, Map<String, RoomInfo>> =
    graph.keys.fold(graph.keys.associateWith { u ->
        graph.keys.associateWith { v ->
            if (u == v) 0 else if (graph[u]?.connections?.contains(v) == true) 1 else Int.MAX_VALUE / 2
        }
    }) { outer, k ->
        graph.keys.fold(outer) { inner, i ->
            graph.keys.fold(inner) { acc, j ->
                acc + Pair(i, acc[i]!! + Pair(j, min(acc[i]!![j]!!, acc[i]!![k]!! + acc[k]!![j]!!)))
            }
        }
    }.filter { it.key == "AA" || graph[it.key]!!.flowRate > 0 }.mapValues {
        it.value.filter { graph[it.key]!!.flowRate > 0 && it.value != 0 && it.key != "AA" }
            .mapValues { RoomInfo(it.value + 1, graph[it.key]!!.flowRate) }
    }
