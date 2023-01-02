import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day22Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day22(AocUtils.loadResourceAsString("day22-example.txt"))
        assertThat(exampleA).isEqualTo(0)
        assertThat(exampleB).isEqualTo(0)

        val (realA, realB) = day22(AocUtils.loadResourceAsString("day22.txt"))
        assertThat(realA).isEqualTo(0)
        assertThat(realB).isEqualTo(0)
    }
}