sealed interface Parser {
    data class Node(
        val parent: Node? = null,
        val children: MutableList<Parser> = mutableListOf(),
        val running: MutableList<Char> = mutableListOf()
    ) : Parser {
        fun addLeaf(returnParent: Boolean = false): Node {
            if (running.size > 0) children.add(Leaf(running.joinToString("").toInt()))
            running.clear()
            return if (returnParent) parent!! else this
        }

        fun addNode(): Node {
            children.add(Node(this))
            return children.last() as Node
        }

        fun addToken(token: Char): Node {
            running.add(token)
            return this
        }
    }

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
                    '[' -> acc.addNode()
                    ']' -> acc.addLeaf(returnParent = true)
                    ',' -> acc.addLeaf()
                    else -> acc.addToken(token)
                }
            }.asPacket()
    }
}

sealed interface Packet : Comparable<Packet> {
    data class Node(val children: List<Packet>) : Packet {
        override fun compareTo(other: Packet): Int =
            when (other) {
                is Leaf -> compareTo(Node(listOf(other)))
                is Node -> children.zip(other.children)
                    .map { it.first.compareTo(it.second) }
                    .find { it != 0 } ?: children.size.compareTo(other.children.size)
            }
    }

    data class Leaf(val i: Int) : Packet {
        override fun compareTo(other: Packet): Int =
            when (other) {
                is Leaf -> i.compareTo(other.i)
                is Node -> Node(listOf(this)).compareTo(other)
            }
    }
}

fun day13(input: String): Pair<Int, Int> {
    val all = input.split("\n\n").flatMap { it.split("\n") }.map(Parser.Companion::parse)
    val divPackets = listOf("[[2]]", "[[6]]").map(Parser.Companion::parse)
    return Pair(
        all.chunked(2).withIndex().filter { it.value[0] < it.value[1] }.sumOf { it.index + 1 },
        divPackets.map((all + divPackets).sorted()::indexOf).map { it.inc() }.fold(1) { a, b -> a * b }
    )
}
