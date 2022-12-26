import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day21KtTest {
    @Test
    fun test() {
        val (exampleA, exampleB) = day21(AocUtils.loadResourceAsString("day21-example.txt"))
        assertThat(exampleA).isEqualTo(0)
        assertThat(exampleB).isEqualTo(0)

        val (realA, realB) = day21(AocUtils.loadResourceAsString("day21.txt"))
        assertThat(realA).isEqualTo(0)
        assertThat(realB).isEqualTo(0)
    }
}