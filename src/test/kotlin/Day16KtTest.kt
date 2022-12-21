import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day16KtTest {
    @Test
    fun test() {
        val (exampleA, exampleB) = day16(AocUtils.loadResourceAsString("day16-example.txt"))
        assertThat(exampleA).isEqualTo(1651)
        assertThat(exampleB).isEqualTo(0) // 1447 too low

        val (realA, realB) = day16(AocUtils.loadResourceAsString("day16.txt"))
        assertThat(realA).isEqualTo(0)
        assertThat(realB).isEqualTo(0)
    }
}