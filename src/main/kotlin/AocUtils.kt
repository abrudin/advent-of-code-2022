object AocUtils {
    fun loadResourceAsString(filename: String, noTrim: Boolean = false): String {
        val result = object {}.javaClass.getResource(filename)?.readText()!!
        return if (noTrim) result else result.trim()
    }
}