import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day01Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day1(AocUtils.loadResourceAsString("day1-example.txt"))
        assertThat(exampleA).isEqualTo(24000)
        assertThat(exampleB).isEqualTo(45000)
        val (realA, realB) = day1(AocUtils.loadResourceAsString("day1.txt"))
        assertThat(realA).isEqualTo(69528)
        assertThat(realB).isEqualTo(206152)
    }
}