import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day18KtTest {
    @Test
    fun test() {
        val (exampleA, exampleB) = day18(AocUtils.loadResourceAsString("day18-example.txt"))
        assertThat(exampleA).isEqualTo(0)
        assertThat(exampleB).isEqualTo(0)

        val (realA, realB) = day18(AocUtils.loadResourceAsString("day18.txt"))
        assertThat(realA).isEqualTo(0)
        assertThat(realB).isEqualTo(0)
    }
}