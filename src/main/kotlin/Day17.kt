data class Context(val pos: Set<Pair<Int, Int>> = setOf(), val index: Int = 0)

data class Rock(val pos: Set<Pair<Int, Int>>) {
    fun move(x: Int, y: Int): Rock = Rock(pos.map { Pair(it.first + x, it.second + y) }.toSet())

    fun blow(context: Context, wind: Int): Rock = move(wind, 0).ifValidOrElse(context, this).first

    fun fall(context: Context): Pair<Rock, Boolean> = move(0, -1).ifValidOrElse(context, this)

    private fun ifValidOrElse(context: Context, rock: Rock): Pair<Rock, Boolean> =
        if (pos.all { it.second >= 0 && it.first in 0..6 && !context.pos.contains(Pair(it.first, it.second)) })
            Pair(this, true)
        else Pair(rock, false)
}

fun day17(input: String): Pair<Int, Int> {
    val wind = input.map { it - '=' }

    tailrec fun blowAndFall(rock: Rock, ctx: Context): Context {
        val (newRock, stillMoving) = rock.blow(ctx, wind[ctx.index % wind.size]).fall(ctx)
        return if (stillMoving) blowAndFall(newRock, Context(ctx.pos, ctx.index + 1))
        else Context(ctx.pos + newRock.pos, ctx.index + 1)
    }

    val rocks = listOf(
        Rock(setOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(3, 0))),
        Rock(setOf(Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(1, 2), Pair(2, 1))),
        Rock(setOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(2, 1), Pair(2, 2))),
        Rock(setOf(Pair(0, 0), Pair(0, 1), Pair(0, 2), Pair(0, 3))),
        Rock(setOf(Pair(0, 0), Pair(0, 1), Pair(1, 0), Pair(1, 1)))
    )

    val context = (0 until 2022).fold(Context()) { ctx, it ->
        blowAndFall(rocks[it % rocks.size].move(2, (ctx.pos.maxOfOrNull { it.second } ?: -1) + 4), ctx)
    }


    return Pair(context.pos.maxOf { it.second } + 1, 0)
}
