object AocUtils {
    fun loadResourceAsString(filename: String, noTrim: Boolean = false): String {
        val result = object {}.javaClass.getResource(filename)?.readText()!!
        return if (noTrim) result else result.trim()
    }

    fun <T> Iterable<T>.takeWhileInclusive(pred: (T) -> Boolean): List<T> {
        var shouldContinue = true
        return takeWhile {
            val result = shouldContinue; shouldContinue = pred(it); result
        }
    }
}