import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day14Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day14(AocUtils.loadResourceAsString("day14-example.txt"))
        assertThat(exampleA).isEqualTo(24)
        assertThat(exampleB).isEqualTo(93)

        val (realA, realB) = day14(AocUtils.loadResourceAsString("day14.txt"))
        assertThat(realA).isEqualTo(964)
        assertThat(realB).isEqualTo(32041)
    }
}