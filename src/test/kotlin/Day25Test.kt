import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day25Test {
    @Test
    fun test() {
        val example = day25(AocUtils.loadResourceAsString("day25-example.txt"))
        assertThat(example).isEqualTo("2=-1=0")

        val real = day25(AocUtils.loadResourceAsString("day25.txt"))
        assertThat(real).isEqualTo("20-1-11==0-=0112-222")
    }
}