fun main() {
    val id = "03"

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            line
                .chunked(line.length / 2)
                .map { it.toSet() }
                .reduce { acc, it -> acc.intersect(it) }
                .first()
                .let {
                    if (it.code > 96)
                        it.code - 96
                    else
                        it.code - 38
                }
        }
    }

    fun part2(input: List<String>): Int {
        return input.windowed(3, 3).sumOf { line ->
            line
                .map { it.toSet() }
                .reduce { acc, i -> acc.intersect(i) }
                .let {
                    val i = it.first()
                    if (i.code > 96)
                        i.code - 96
                    else
                        i.code - 38
                }
        }
    }

    val testInput = readInput("Day${id}_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day${id}")
    println("==== DAY $id ====")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
