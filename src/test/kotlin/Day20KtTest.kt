import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day20KtTest {
    @Test
    fun test() {
        val (exampleA, exampleB) = day20(AocUtils.loadResourceAsString("day20-example.txt"))
        assertThat(exampleA).isEqualTo(0)
        assertThat(exampleB).isEqualTo(0)

        val (realA, realB) = day20(AocUtils.loadResourceAsString("day20.txt"))
        assertThat(realA).isEqualTo(0)
        assertThat(realB).isEqualTo(0)
    }
}