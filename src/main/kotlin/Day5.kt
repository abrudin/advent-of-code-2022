fun day5(input: String): Pair<String, String> {
    data class Move(val moveAmount: Int, val from: Int, val to: Int)
    data class Stacks(val value: List<List<Char>>) {
        constructor(size: Int) : this(1.rangeTo(size).map { listOf<Char>() })

        fun topFromEach(): String = value.map { it.last() }.joinToString("")

        fun makeMoveA(move: Move): Stacks =
            Stacks(1.rangeTo(move.moveAmount).fold(value) { stacks, _ -> makeMove(stacks, move, 1) })

        fun makeMoveB(move: Move): Stacks = Stacks(makeMove(value, move, move.moveAmount))

        private fun makeMove(stacks: List<List<Char>>, move: Move, amount: Int) = stacks.withIndex().map {
            when (it.index) {
                move.from -> it.value.dropLast(amount)
                move.to -> it.value + stacks[move.from].takeLast(amount)
                else -> it.value
            }
        }
    }

    val parsed = input.split("\n\n").map { it.split("\n").dropLast(1) }
    val numStacks = parsed[0].last().split(" ").size

    val stacks = parsed[0].reversed().map { it.chunked(4).map { it[1] } }
        .fold(Stacks(numStacks)) { stacks, row ->
            Stacks(stacks.value.withIndex().map {
                if (row.getOrElse(it.index) { ' ' } != ' ') it.value + row[it.index] else it.value
            })
        }

    val moves = parsed[1].map { it.split(" ").chunked(2).map { it[1].toInt() } }
        .map { Move(moveAmount = it[0], from = it[1] - 1, to = it[2] - 1) }

    return Pair(
        moves.fold(stacks) { prevStacks, move -> prevStacks.makeMoveA(move) }.topFromEach(),
        moves.fold(stacks) { prevStacks, move -> prevStacks.makeMoveB(move) }.topFromEach()
    )
}