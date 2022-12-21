import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day17KtTest {
    @Test
    fun test() {
        val (exampleA, exampleB) = day17(AocUtils.loadResourceAsString("day17-example.txt"))
        assertThat(exampleA).isEqualTo(0)
        assertThat(exampleB).isEqualTo(0)

        val (realA, realB) = day17(AocUtils.loadResourceAsString("day17.txt"))
        assertThat(realA).isEqualTo(0)
        assertThat(realB).isEqualTo(0)
    }
}