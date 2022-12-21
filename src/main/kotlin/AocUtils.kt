object AocUtils {
    fun loadResourceAsString(filename: String, noTrim: Boolean = false): String {
        val result = object {}.javaClass.getResource(filename)?.readText()!!
        return if (noTrim) result else result.trim()
    }

    fun visualize(set: Set<Pair<Int, Int>>, orig: Set<Pair<Int, Int>>): String {
        val minX = set.minOf { it.first }
        val minY = 0
        val maxX = set.maxOf { it.first }
        val maxY = set.maxOf { it.second }
        fun pixelAt(x: Int, y: Int): Char =
            if (orig.contains(Pair(x, y))) '#'
            else if (set.contains(Pair(x, y))) 'o'
            else '.'
        return (minY..maxY).map { y ->
            (minX..maxX).map { x -> pixelAt(x, y) }.joinToString("")
        }.joinToString("\n")
    }
}