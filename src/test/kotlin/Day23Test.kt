import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day23Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day23(AocUtils.loadResourceAsString("day23-example.txt"))
        assertThat(exampleA).isEqualTo(0)
        assertThat(exampleB).isEqualTo(0)

        val (realA, realB) = day23(AocUtils.loadResourceAsString("day23.txt"))
        assertThat(realA).isEqualTo(0)
        assertThat(realB).isEqualTo(0)
    }
}