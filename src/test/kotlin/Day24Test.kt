import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day24Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day24(AocUtils.loadResourceAsString("day24-example.txt"))
        assertThat(exampleA).isEqualTo(18)
        assertThat(exampleB).isEqualTo(54)

        val (realA, realB) = day24(AocUtils.loadResourceAsString("day24.txt"))
        assertThat(realA).isEqualTo(271)
        assertThat(realB).isEqualTo(813)
    }
}