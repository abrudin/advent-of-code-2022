import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day1KtTest {
    @Test
    fun test() {
        val example = day1(AocUtils.loadResourceAsString("day1-example.txt"))
        assertThat(example.first).isEqualTo(24000)
        assertThat(example.second).isEqualTo(45000)
        val real = day1(AocUtils.loadResourceAsString("day1.txt"))
        assertThat(real.first).isEqualTo(69528)
        assertThat(real.second).isEqualTo(206152)
    }
}