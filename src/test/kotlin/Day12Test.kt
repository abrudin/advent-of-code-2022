import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day12Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day12(AocUtils.loadResourceAsString("day12-example.txt"))
        assertThat(exampleA).isEqualTo(31)
        assertThat(exampleB).isEqualTo(29)

        val (realA, realB) = day12(AocUtils.loadResourceAsString("day12.txt"))
        assertThat(realA).isEqualTo(468)
        assertThat(realB).isEqualTo(459)
    }
}