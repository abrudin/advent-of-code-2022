import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day3KtTest {
    @Test
    fun test() {
        val inputEx = AocUtils.loadResourceAsString("day3-example.txt")
        assertThat(day3(inputEx).first).isEqualTo(157)
        assertThat(day3(inputEx).second).isEqualTo(70)
        val inputReal = AocUtils.loadResourceAsString("day3.txt")
        assertThat(day3(inputReal).first).isEqualTo(8233)
        assertThat(day3(inputReal).second).isEqualTo(2821)
    }
}