//[ , M,  ,  ,  , Z,  , V,  ],
//[ , Z,  , P,  , L,  , Z, J],
//[S, D,  , W,  , W,  , H, Q],
//[P, V, N, D,  , P,  , C, V],
//[H, B, J, V, B, M,  , N, P],
//[V, F, L, Z, C, S, P, S, G],
//[F, J, M, G, R, R, H, R, L],
//[G, G, G, N, V, V, T, Q, F]

fun main() {
    val id = "05"

    fun part1(input: List<String>): String {
        val crates = input.slice(0..7)
            .map { it.split("")
                .drop(1)
                .chunked(4)
                .map { it[1] }
            }
            .let { matrix ->
                List(9) { List(8) { "" } }
                    .mapIndexed { idx1, it1 ->
                        List(it1.size) { idx2 -> matrix[idx2][idx1] }
                            .reversed()
                            .filter { it.trim() != "" }
                    }
            }
            .map { it
                .toMutableList()
            }

        return input.drop(10).map {
            val (n, from, to) = it.split("""move|from|to"""
                .toRegex())
                .drop(1)
                .map { it.trim().toInt() }

            repeat(n) {
                val a = crates[from - 1].removeLast()
                crates[to - 1].add(a)
            }
            crates.map { it }
        }.let {
            crates.drop(1).map { it.last() }
        }.joinToString("").also(::println)
    }

    fun part2(input: List<String>): String {
        val crates = input.slice(0..7)
            .map { it.split("")
                .drop(1)
                .chunked(4)
                .map { it[1] }
            }
            .let { matrix ->
                List(9) { List(8) { "" } }
                    .mapIndexed { idx1, it1 ->
                        List(it1.size) { idx2 -> matrix[idx2][idx1] }
                            .reversed()
                            .filter { it.trim() != "" }
                    }
            }
            .map { it
                .toMutableList()
            }

        return input.map {
            val (n, from, to) = it.split("""move|from|to""".toRegex()).drop(1).map { it.trim().toInt() }

            val a = crates[from].takeLast(n)
            crates[to].addAll(a)

            repeat(n) {
                crates[from].removeLast()
            }

            crates.map { it }
        }.let {
            crates.drop(1).map { it.last() }
        }.joinToString("").also(::println)
    }

//    val testInput = readInput("Day${id}_test")
//    check(part1(testInput) == "CMZ")

    val input = readInput("Day${id}")
    println("==== DAY $id ====")
    println("Part 1: ${part1(input)}")  
    println("Part 2: ${part2(input)}")
}
