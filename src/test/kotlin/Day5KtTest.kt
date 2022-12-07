import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day5KtTest {
    @Test
    fun test() {
        val example = day5(AocUtils.loadResourceAsString("day5-example.txt", true))
        assertThat(example.first).isEqualTo("CMZ")
        assertThat(example.second).isEqualTo("MCD")
        
        val real = day5(AocUtils.loadResourceAsString("day5.txt", true))
        assertThat(real.first).isEqualTo("SHQWSRBDL")
        assertThat(real.second).isEqualTo("CDTQZHBRS")
    }
}