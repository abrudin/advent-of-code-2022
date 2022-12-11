fun day11(input: String): Pair<Long, Long> {
    data class Monkey(
        val items: List<Long>,
        val op: (Long) -> Long,
        val divisor: Long,
        val mTrue: Int,
        val mFalse: Int,
        val inspections: Long = 0
    )

    val divisors = input.split("\n\n").map { it.split("\n").drop(3).first().split(" ").last().trim().toLong() }
        .fold(1L) { a, b -> a * b }

    val monkeys = input.split("\n\n").map { monkey ->
        Monkey(
            monkey.split("\n").drop(1).first().split(":").last().split(",").map { it.trim().toLong() },
            { b: Long ->
                val op = monkey.split("\n").drop(2).first().split("=").last()
                val v1 = Regex("old \\* (\\d+)").find(op)?.destructured?.component1()
                val v2 = Regex("old \\+ (\\d+)").find(op)?.destructured?.component1()
                val v3 = Regex("old \\* old").containsMatchIn(op)
                if (v1 != null) {
                    (b * v1.toLong()).mod(divisors)
                } else if (v2 != null) {
                    (b + v2.toLong()).mod(divisors)
                } else if (v3) {
                    (b * b).mod(divisors)
                } else throw Exception("Unknown expression: $op")
            },
            monkey.split("\n").drop(3).first().split(" ").last().trim().toLong(),
            monkey.split("\n").drop(4).first().split(" ").last().trim().toInt(),
            monkey.split("\n").drop(5).first().split(" ").last().trim().toInt()
        )
    }

    fun inspect(monkeys: List<Monkey>, index: Int, divisor: Long): List<Monkey> {
        val currentMonkey = monkeys[index]
        val newWorryLevel = (currentMonkey.op.invoke(currentMonkey.items.first()) / divisor)
        return monkeys.mapIndexed { i, m ->
            if (i == index) {
                Monkey(m.items.drop(1), m.op, m.divisor, m.mTrue, m.mFalse, m.inspections.inc())
            } else if (i == currentMonkey.mTrue && newWorryLevel.mod(currentMonkey.divisor) == 0L) {
                Monkey(m.items + newWorryLevel, m.op, m.divisor, m.mTrue, m.mFalse, m.inspections)
            } else if (i == currentMonkey.mFalse && newWorryLevel.mod(currentMonkey.divisor) != 0L) {
                Monkey(m.items + newWorryLevel, m.op, m.divisor, m.mTrue, m.mFalse, m.inspections)
            } else {
                m
            }
        }
    }

    fun inspectAll(monkeys: List<Monkey>, divisor: Long): List<Monkey> =
        monkeys.indices.fold(monkeys) { monkeys, index ->
            monkeys[index].items.indices.fold(monkeys) { monkeys, _ -> inspect(monkeys, index, divisor) }
        }
    return Pair(
        (0 until 20).fold(monkeys) { ms, _ -> inspectAll(ms, 3) }.map { it.inspections }.sortedDescending().take(2)
            .fold(1L) { a, b -> a * b },
        (0 until 10000).fold(monkeys) { ms, _ -> inspectAll(ms, 1) }.map { it.inspections }.sortedDescending().take(2)
            .fold(1L) { a, b -> a * b }
    )
}
