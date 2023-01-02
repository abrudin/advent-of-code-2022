import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day05Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day5(AocUtils.loadResourceAsString("day5-example.txt", true))
        assertThat(exampleA).isEqualTo("CMZ")
        assertThat(exampleB).isEqualTo("MCD")

        val (realA, realB) = day5(AocUtils.loadResourceAsString("day5.txt", true))
        assertThat(realA).isEqualTo("SHQWSRBDL")
        assertThat(realB).isEqualTo("CDTQZHBRS")
    }
}