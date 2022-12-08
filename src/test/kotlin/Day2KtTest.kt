import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day2KtTest {
    @Test
    fun test() {
        val (exampleA, exampleB) = day2(AocUtils.loadResourceAsString("day2-example.txt"))
        assertThat(exampleA).isEqualTo(15)
        assertThat(exampleB).isEqualTo(12)
        val (realA, realB) = day2(AocUtils.loadResourceAsString("day2.txt"))
        assertThat(realA).isEqualTo(11841)
        assertThat(realB).isEqualTo(13022)
    }
}