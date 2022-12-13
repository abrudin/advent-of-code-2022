import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day13KtTest {
    @Test
    fun test() {
        val (exampleA, exampleB) = day13(AocUtils.loadResourceAsString("day13-example.txt"))
        assertThat(exampleA).isEqualTo(13)
        assertThat(exampleB).isEqualTo(140)

        val (realA, realB) = day13(AocUtils.loadResourceAsString("day13.txt"))
        assertThat(realA).isEqualTo(5196) // Too low, 5215 too high
        assertThat(realB).isEqualTo(22134)
    }
}