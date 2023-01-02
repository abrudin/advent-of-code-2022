import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day09Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day9(AocUtils.loadResourceAsString("day9-example.txt"))
        assertThat(exampleA).isEqualTo(13)
        assertThat(exampleB).isEqualTo(1)

        val (realA, realB) = day9(AocUtils.loadResourceAsString("day9.txt"))
        assertThat(realA).isEqualTo(5874)
        assertThat(realB).isEqualTo(2467)
    }
}