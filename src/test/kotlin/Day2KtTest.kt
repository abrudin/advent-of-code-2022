import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day2KtTest {
    @Test
    fun test() {
        val inputEx = AocUtils.loadResourceAsString("day2-example.txt")
        assertThat(day2(inputEx).first).isEqualTo(15)
        assertThat(day2(inputEx).second).isEqualTo(12)
        val inputReal = AocUtils.loadResourceAsString("day2.txt")
        assertThat(day2(inputReal).first).isEqualTo(11841)
        assertThat(day2(inputReal).second).isEqualTo(13022)
    }
}