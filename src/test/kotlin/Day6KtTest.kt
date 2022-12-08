import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day6KtTest {
    @Test
    fun test() {
        val (exampleA, exampleB) = day6(AocUtils.loadResourceAsString("day6-example.txt", true))
        assertThat(exampleA).isEqualTo(7)
        assertThat(exampleB).isEqualTo(19)

        val (realA, realB) = day6(AocUtils.loadResourceAsString("day6.txt", true))
        assertThat(realA).isEqualTo(1647)
        assertThat(realB).isEqualTo(2447)
    }
}