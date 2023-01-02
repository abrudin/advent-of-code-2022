enum class Op(val apply: (Expr, Expr) -> Expr.Const) {
    ADD(fun(a, b) = Expr.Const((a as Expr.Const).value + (b as Expr.Const).value)),
    SUB(fun(a, b) = Expr.Const((a as Expr.Const).value - (b as Expr.Const).value)),
    MUL(fun(a, b) = Expr.Const((a as Expr.Const).value * (b as Expr.Const).value)),
    DIV(fun(a, b) = Expr.Const((a as Expr.Const).value / (b as Expr.Const).value)),
    EQ(fun(left, right) = (when (left) {
        is Expr.X -> right // Equation solved: x = C => C
        is Expr.Const -> EQ.apply(right, left) // Flip equation: C = f(x) => f(x) = C
        is Expr.FX -> when (left.left) { // Disassemble left side
            is Expr.Const -> EQ.apply( // f(x) = C => C1 `op` g(x) = C
                left.right, when (left.op) {
                    ADD -> Expr(right, SUB, left.left) // a + f(x) = b => f(x) = b - a
                    SUB -> Expr(left.left, SUB, right) // a - f(x) = b => f(x) = a - b
                    MUL -> Expr(right, DIV, left.left) // a * f(x) = b => f(x) = b / a
                    DIV -> Expr(left.left, DIV, right) // a / f(x) = b => f(x) = a / b
                    EQ -> throw Exception("Multiple = not allowed")
                }
            )

            is Expr.X, is Expr.FX -> EQ.apply( // f(x) = C => g(x) `op` C1 = C
                left.left, when (left.op) {
                    ADD -> Expr(right, SUB, left.right) // f(x) + a = b => f(x) = b - a
                    SUB -> Expr(right, ADD, left.right) // f(x) - a = b => f(x) = b + a
                    MUL -> Expr(right, DIV, left.right) // f(x) * a = b => f(x) = b / a
                    DIV -> Expr(right, MUL, left.right) // f(x) / a = b => f(x) = b * a
                    EQ -> throw Exception("Multiple = not allowed")
                }
            )
        }
    }) as Expr.Const);

    companion object {
        fun from(s: String) = when (s) {
            "+" -> ADD
            "-" -> SUB
            "*" -> MUL
            "/" -> DIV
            "=" -> EQ
            else -> throw Exception("Unknown operation")
        }
    }
}

sealed interface Expr {
    data class Const(val value: Long) : Expr
    data class X(val ignore: Boolean) : Expr
    data class FX(val left: Expr, val op: Op, val right: Expr) : Expr
    companion object {
        operator fun invoke(left: Expr, op: Op, right: Expr): Expr =
            if (op == Op.EQ || left is Const && right is Const) op.apply(left, right)
            else FX(left, op, right)
    }
}

fun day21(input: String): Pair<Long, Long> {
    fun parse(map: Map<String, String>, current: String = "root"): Expr =
        if (map[current]!! == "x") Expr.X(true)
        else if (map[current]!!.toLongOrNull() != null) Expr.Const(map[current]!!.toLong())
        else listOf("+", "-", "*", "/", "=").find { map[current]!!.contains(it) }!!.let {
            val (first, second) = map[current]!!.split(it).map { it.trim() }
            Expr(parse(map, first), Op.from(it), parse(map, second))
        }

    val aMap = input.split("\n").associate { Pair(it.split(":")[0], it.split(":")[1].trim()) }
    val bMap = aMap +
            Pair("humn", "x") +
            Pair("root", aMap["root"]!!.replace("+", "=").replace("-", "=").replace("*", "=").replace("/", "="))

    return Pair(
        (parse(aMap) as Expr.Const).value,
        (parse(bMap) as Expr.Const).value
    )
}
