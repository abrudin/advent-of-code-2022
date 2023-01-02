import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day04Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day4(AocUtils.loadResourceAsString("day4-example.txt"))
        assertThat(exampleA).isEqualTo(2)
        assertThat(exampleB).isEqualTo(4)
        val (realA, realB) = day4(AocUtils.loadResourceAsString("day4.txt"))
        assertThat(realA).isEqualTo(471)
        assertThat(realB).isEqualTo(888)
    }
}