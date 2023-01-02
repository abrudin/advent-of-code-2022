import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day19Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day19(AocUtils.loadResourceAsString("day19-example.txt"))
        assertThat(exampleA).isEqualTo(0)
        assertThat(exampleB).isEqualTo(0)

        val (realA, realB) = day19(AocUtils.loadResourceAsString("day19.txt"))
        assertThat(realA).isEqualTo(0)
        assertThat(realB).isEqualTo(0)
    }
}