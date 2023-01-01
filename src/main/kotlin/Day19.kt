enum class Type { ORE, CLAY, OBSIDIAN, GEODE }

fun day19(input: String): Pair<Int, Int> {
    data class Material(val amount: Int, val type: Type)
    data class Robot(val amount: Int, val type: Type, val creationTime: Int)
    data class Recipe(val input: Set<Material>, val output: Type)
    data class Recipes(
        val oreRecipe: Set<Material>,
        val clayRecipe: Set<Material>,
        val obsidianRecipe: Set<Material>,
        val geodeRecipe: Set<Material>
    )

    data class State(val robots: Map<Type, Robot>, val materials: Map<Type, Int>) {
        fun update(): State =
            State(robots, materials.mapValues { it.value + (robots[it.key]?.amount ?: 0) })

        fun buyRobot(price: Set<Material>, output: Type): State {
            return State(robots.mapValues {
                if (it.key == output)
                    Robot(it.value.amount + 1, it.key, 0) else it.value
            }, materials.mapValues { material ->
                material.value - (price.find { material.key == it.type }?.amount ?: 0)
            })
        }

        fun buy(recipes: Recipes): Set<State> {
            return setOf(this) +
                    (if (shouldBuy(recipes.geodeRecipe, Type.GEODE)) buyRobot(
                        recipes.geodeRecipe,
                        Type.GEODE
                    ).buy(recipes) else setOf()) +
                    (if (shouldBuy(recipes.obsidianRecipe, Type.OBSIDIAN)) buyRobot(
                        recipes.geodeRecipe,
                        Type.OBSIDIAN
                    ).buy(recipes) else setOf()) +
                    (if (shouldBuy(recipes.clayRecipe, Type.CLAY)) buyRobot(
                        recipes.geodeRecipe,
                        Type.CLAY
                    ).buy(recipes) else setOf()) +
                    (if (shouldBuy(recipes.oreRecipe, Type.ORE)) buyRobot(
                        recipes.geodeRecipe,
                        Type.ORE
                    ).buy(recipes) else setOf())
        }

        private fun shouldBuy(recipe: Set<Material>, type: Type): Boolean =
            recipe.all { materials[it.type]!! >= it.amount } &&
                    robots[type]!!.amount <= (robots.values.filter { it.type != type }
                .maxOf { it.amount } + 1)

        override fun toString(): String {
            return "State:\n  robots:\n    ${robots.values.joinToString("\n    ")}\n  materials:\n    ${
                materials.entries.joinToString("\n    ")
            })"
        }

    }

    val blueprint = Recipes(
        setOf(Material(4, Type.ORE)),
        setOf(Material(2, Type.ORE)),
        setOf(Material(3, Type.ORE), Material(14, Type.CLAY)),
        setOf(Material(2, Type.ORE), Material(7, Type.OBSIDIAN))
    )
    val result = (0 until 24).runningFold(
        setOf(
            State(
                setOf(
                    Robot(1, Type.ORE, 0),
                    Robot(0, Type.CLAY, 0),
                    Robot(0, Type.OBSIDIAN, 0),
                    Robot(0, Type.GEODE, 0)
                ).associateBy { it.type },
                mapOf(Pair(Type.ORE, 0), Pair(Type.CLAY, 0), Pair(Type.OBSIDIAN, 0), Pair(Type.GEODE, 0))
            )
        )
    ) { state, minute ->
        println(minute)
        state.flatMap { it.buy(blueprint).map { it.update() } }.toSet()
    }
    println(result.last().maxBy { it.materials[Type.GEODE]!! })
    return Pair(1, 0)
}
