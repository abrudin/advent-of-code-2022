import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day15KtTest {
    @Test
    fun test() {
        val (exampleA, exampleB) = day15(AocUtils.loadResourceAsString("day15-example.txt"), 10)
        assertThat(exampleA).isEqualTo(26)
        assertThat(exampleB).isEqualTo(56000011)

        val (realA, realB) = day15(AocUtils.loadResourceAsString("day15.txt"), 2000000)
        assertThat(realA).isEqualTo(5083287)
        assertThat(realB).isEqualTo(13134039205729)
    }
}