import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day18Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day18(AocUtils.loadResourceAsString("day18-example.txt"))
        assertThat(exampleA).isEqualTo(64)
        assertThat(exampleB).isEqualTo(58)

        val (realA, realB) = day18(AocUtils.loadResourceAsString("day18.txt"))
        assertThat(realA).isEqualTo(4500)
        assertThat(realB).isEqualTo(2558)
    }
}