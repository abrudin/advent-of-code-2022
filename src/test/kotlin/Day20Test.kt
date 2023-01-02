import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day20Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day20(AocUtils.loadResourceAsString("day20-example.txt"))
        assertThat(exampleA).isEqualTo(3)
        assertThat(exampleB).isEqualTo(1623178306)

        val (realA, realB) = day20(AocUtils.loadResourceAsString("day20.txt"))
        assertThat(realA).isEqualTo(7153)
        assertThat(realB).isEqualTo(6146976244822)

    }
}