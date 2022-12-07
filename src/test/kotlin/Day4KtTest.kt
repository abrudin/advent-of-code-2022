import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day4KtTest {
    @Test
    fun test() {
        val inputEx = AocUtils.loadResourceAsString("day4-example.txt")
        assertThat(day4(inputEx).first).isEqualTo(2)
        assertThat(day4(inputEx).second).isEqualTo(4)
        val inputReal = AocUtils.loadResourceAsString("day4.txt")
        assertThat(day4(inputReal).first).isEqualTo(471)
        assertThat(day4(inputReal).second).isEqualTo(888)
    }
}