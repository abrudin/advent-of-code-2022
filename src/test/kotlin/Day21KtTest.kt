import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day21KtTest {
    @Test
    fun test() {
        val (exampleA, exampleB) = day21(AocUtils.loadResourceAsString("day21-example.txt"))
        assertThat(exampleA).isEqualTo(152)
        assertThat(exampleB).isEqualTo(301)

        val (realA, realB) = day21(AocUtils.loadResourceAsString("day21.txt"))
        assertThat(realA).isEqualTo(104272990112064)
        assertThat(realB).isEqualTo(3220993874133)
    }
}