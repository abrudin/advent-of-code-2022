import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day6KtTest {
    @Test
    fun test() {
        val example = day6(AocUtils.loadResourceAsString("day6-example.txt", true))
        assertThat(example.first).isEqualTo(7)
        assertThat(example.second).isEqualTo(19)

        val real = day6(AocUtils.loadResourceAsString("day6.txt", true))
        assertThat(real.first).isEqualTo(1647)
        assertThat(real.second).isEqualTo(2447)
    }
}