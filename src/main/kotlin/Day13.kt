fun <T> List<T>.zipFill(other: List<T>, fill: T): List<Pair<T, T>> =
    (this + if (other.size > this.size) List(other.size - this.size) { fill } else listOf())
        .zip(other + if (this.size > other.size) List(this.size - other.size) { fill } else listOf())

sealed interface Packet : Comparable<Packet> {
    override operator fun compareTo(other: Packet): Int =
        when (this) {
            is Leaf -> when (other) {
                is Leaf -> this.i.compareTo(other.i)
                is Node -> if (this.i < 0) -1 else Node(listOf(this)).compareTo(other)
            }

            is Node -> when (other) {
                is Leaf -> if (other.i < 0) 1 else this.compareTo(Node(listOf(other)))
                is Node -> this.children.zipFill(other.children, Leaf(-1))
                    .map { it.first.compareTo(it.second) }.find { it != 0 } ?: 0
            }
        }


    data class Node(val children: List<Packet>) : Packet

    data class Leaf(val i: Int) : Packet
}

sealed interface Parser {
    data class Node(
        val parent: Node? = null,
        val children: MutableList<Parser> = mutableListOf(),
        val running: MutableList<Char> = mutableListOf()
    ) : Parser

    data class Leaf(val i: Int) : Parser

    fun asPacket(): Packet =
        when (this) {
            is Node -> Packet.Node(children.map { it.asPacket() })
            is Leaf -> Packet.Leaf(i)
        }

    companion object {
        fun parse(input: String) =
            input.fold(Node()) { acc, token ->
                when (token) {
                    '[' -> {
                        acc.children.add(Node(acc)); acc.children.last() as Node
                    }

                    ']' -> {
                        if (acc.running.size > 0) acc.children.add(Leaf(acc.running.joinToString("").toInt()))
                        acc.running.clear(); acc.parent!!
                    }

                    ',' -> {
                        if (acc.running.size > 0) acc.children.add(Leaf(acc.running.joinToString("").toInt()))
                        acc.running.clear(); acc
                    }

                    else -> {
                        acc.running.add(token); acc
                    }
                }
            }.asPacket()
    }
}

fun day13(input: String): Pair<Int, Int> {
    val all = input.split("\n\n").flatMap { it.split("\n") }.map(Parser.Companion::parse)
    val divPacket1: Packet = Packet.Node(listOf(Packet.Node(listOf(Packet.Leaf(2)))))
    val divPacket2: Packet = Packet.Node(listOf(Packet.Node(listOf(Packet.Leaf(6)))))
    val allWithDividers = ((all + divPacket1) + divPacket2).sorted().toList()
    return Pair(
        all.chunked(2).withIndex().filter { it.value[0] < it.value[1] }.sumOf { it.index + 1 },
        listOf(divPacket1, divPacket2).map(allWithDividers::indexOf).map { it.inc() }.fold(1) { a, b -> a * b }
    )
}
