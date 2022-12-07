fun day2(input: String): Pair<Int, Int> {
    data class Round(val opponent: Int, val you: Int) {
        fun points() = you + 1 + (you - opponent + 1).mod(3) * 3
        fun winDrawLooseToChoice() = Round(opponent, (opponent + you - 1).mod(3))
    }

    val rounds = input.split("\n").map { Round(it[0] - 'A', it[2] - 'X') }

    return Pair(
        rounds.sumOf { it.points() },
        rounds.sumOf { it.winDrawLooseToChoice().points() }
    )
}
