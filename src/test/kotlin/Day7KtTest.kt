import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day7KtTest {
    @Test
    fun test() {
        val example = day7(AocUtils.loadResourceAsString("day7-example.txt"))
        assertThat(example.first).isEqualTo(95437)
        assertThat(example.second).isEqualTo(24933642)

        val real = day7(AocUtils.loadResourceAsString("day7.txt"))
        assertThat(real.first).isEqualTo(1989474)
        assertThat(real.second).isEqualTo(1111607)
    }
}