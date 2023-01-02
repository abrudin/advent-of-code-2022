import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day11Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day11(AocUtils.loadResourceAsString("day11-example.txt"))
        assertThat(exampleA).isEqualTo(10605)
        assertThat(exampleB).isEqualTo(2713310158)

        val (realA, realB) = day11(AocUtils.loadResourceAsString("day11.txt"))
        assertThat(realA).isEqualTo(90294)
        assertThat(realB).isEqualTo(18170818354)
    }
}