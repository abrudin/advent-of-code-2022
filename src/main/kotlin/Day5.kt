fun day5(input: String): Pair<String, String> {
    data class Move(val amount: Int, val from: Int, val to: Int)

    val (initStacks, initMoves) = input.split("\n\n").map { it.trimEnd() }
    val numStacks = initStacks.last().digitToInt()

    val stacks = initStacks.split("\n").dropLast(1).map { it.chunked(4).map { it[1] } }
        .fold(List(numStacks) { listOf<Char>() }) { stacks, row ->
            stacks.mapIndexed { index, value -> value + row.getOrElse(index) { ' ' } }
        }.map { it.reversed().takeWhile { it != ' ' } }

    val moves = initMoves.split("\n").map {
        val (amount, from, to) = "move (\\d+) from (\\d+) to (\\d+)".toRegex().matchEntire(it)!!.destructured
        Move(amount.toInt(), from.toInt() - 1, to.toInt() - 1)
    }

    fun move(stacks: List<List<Char>>, move: Move, amount: Int): List<List<Char>> = stacks.mapIndexed { index, value ->
        when (index) {
            move.from -> value.dropLast(amount)
            move.to -> value + stacks[move.from].takeLast(amount)
            else -> value
        }
    }

    return Pair(
        moves.fold(stacks) { prev, move -> (1..move.amount).fold(prev) { stacks, _ -> move(stacks, move, 1) } }
            .map { it.last() }.joinToString(""),
        moves.fold(stacks) { prevStacks, move -> move(prevStacks, move, move.amount) }
            .map { it.last() }.joinToString("")
    )
}