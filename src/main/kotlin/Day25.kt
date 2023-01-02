import kotlin.streams.toList

fun day25(input: String): String {
    fun power(base: Long, exponent: Int): Long = (1..exponent).fold(1) { acc, _ -> acc * base }
    fun fromSnafu(snafu: String) = snafu.chars().map {
        when (it) {
            '-'.code -> -1
            '='.code -> -2
            else -> it - '0'.code
        }
    }.toList().reversed().withIndex().sumOf { it.value * power(5, it.index) }

    val extra = (0 until 25).sumOf { 2 * power(5, it) }
    
    fun toSnafu(decimal: Long): String {
        return (decimal + extra).toString(5).map {
            when (it) {
                '0' -> '='
                '1' -> '-'
                else -> it - 2
            }
        }.joinToString("").trimStart('0')
    }

    return toSnafu(input.split("\n").sumOf { fromSnafu(it) })
}
