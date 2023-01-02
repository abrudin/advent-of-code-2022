import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day10Test {
    @Test
    fun test() {
        val (exampleA, exampleB) = day10(AocUtils.loadResourceAsString("day10-example.txt"))
        assertThat(exampleA).isEqualTo(13140)
        assertThat(exampleB).isEqualTo(
            """
            ██..██..██..██..██..██..██..██..██..██..
            ███...███...███...███...███...███...███.
            ████....████....████....████....████....
            █████.....█████.....█████.....█████.....
            ██████......██████......██████......████
            ███████.......███████.......███████.....
            """.trimIndent()
        )

        val (realA, realB) = day10(AocUtils.loadResourceAsString("day10.txt"))
        assertThat(realA).isEqualTo(13860)
        assertThat(realB).isEqualTo(
            """
            ███..████.█..█.████..██....██..██..███..
            █..█....█.█..█.█....█..█....█.█..█.█..█.
            █..█...█..████.███..█.......█.█....███..
            ███...█...█..█.█....█.██....█.█....█..█.
            █.█..█....█..█.█....█..█.█..█.█..█.█..█.
            █..█.████.█..█.█.....███..██...██..███..
            """.trimIndent()
        )
    }
}