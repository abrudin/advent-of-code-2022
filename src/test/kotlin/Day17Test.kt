import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day17Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day17(AocUtils.loadResourceAsString("day17-example.txt"))
        assertThat(exampleA).isEqualTo(3068)
        //assertThat(exampleB).isEqualTo(1514285714288)

        val (realA, realB) = day17(AocUtils.loadResourceAsString("day17.txt"))
        assertThat(realA).isEqualTo(3215)
        //assertThat(realB).isEqualTo(0)
    }
}