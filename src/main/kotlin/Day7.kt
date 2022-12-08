data class Dir(
    val name: String,
    val parent: Dir? = null,
    val children: MutableList<Dir> = mutableListOf(),
    val files: MutableList<Int> = mutableListOf()
) {
    fun findRoot(): Dir = parent?.findRoot() ?: this
    fun size(): Int = files.sum() + children.sumOf { it.size() }
    fun dirs(): List<Dir> = children.flatMap { it.dirs() } + this
}

sealed interface Cmd {
    data class Cd(val name: String) : Cmd
    object Ls : Cmd
    data class LsDir(val name: String) : Cmd
    data class LsFile(val size: Int) : Cmd
    companion object {
        fun from(cmd: String): Cmd =
            if (cmd.startsWith("$ cd")) Cd(cmd.split(" ").last())
            else if (cmd.startsWith("dir")) LsDir(cmd.split(" ").last())
            else if (cmd.startsWith("$ ls")) Ls
            else LsFile(cmd.split(" ").first().toInt())
    }
}

fun day7(input: String): Pair<Int, Int> {
    val root = input.split("\n").map { Cmd.from(it) }.drop(1).fold(Dir("/")) { current, cmd ->
        when (cmd) {
            is Cmd.Cd -> current.children.find { it.name == cmd.name } ?: current.parent!!
            is Cmd.Ls -> current
            is Cmd.LsDir -> {
                current.children.add(Dir(cmd.name, current)); current
            }

            is Cmd.LsFile -> {
                current.files.add(cmd.size); current
            }
        }
    }.findRoot()
    return Pair(
        root.dirs().filter { it.size() <= 100000 }.sumOf { it.size() },
        root.dirs().sortedBy { it.size() }.first { 30000000 + root.size() - it.size() <= 70000000 }.size()
    )
}
