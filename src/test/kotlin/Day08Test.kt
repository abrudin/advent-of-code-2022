import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day08Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day8(AocUtils.loadResourceAsString("day8-example.txt"))
        assertThat(exampleA).isEqualTo(21)
        assertThat(exampleB).isEqualTo(8)

        val (realA, realB) = day8(AocUtils.loadResourceAsString("day8.txt"))
        assertThat(realA).isEqualTo(1829)
        assertThat(realB).isEqualTo(291840)
    }
}