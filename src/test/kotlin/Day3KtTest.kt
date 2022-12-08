import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day3KtTest {
    @Test
    fun test() {
        val (exampleA, exampleB) = day3(AocUtils.loadResourceAsString("day3-example.txt"))
        assertThat(exampleA).isEqualTo(157)
        assertThat(exampleB).isEqualTo(70)
        val (realA, realB) = day3(AocUtils.loadResourceAsString("day3.txt"))
        assertThat(realA).isEqualTo(8233)
        assertThat(realB).isEqualTo(2821)
    }
}