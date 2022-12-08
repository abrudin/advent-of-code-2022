import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day7KtTest {
    @Test
    fun test() {
        val (exampleA, exampleB) = day7(AocUtils.loadResourceAsString("day7-example.txt"))
        assertThat(exampleA).isEqualTo(95437)
        assertThat(exampleB).isEqualTo(24933642)

        val (realA, realB) = day7(AocUtils.loadResourceAsString("day7.txt"))
        assertThat(realA).isEqualTo(1989474)
        assertThat(realB).isEqualTo(1111607)
    }
}