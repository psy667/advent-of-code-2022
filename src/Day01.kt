fun main() {
    val id = "01"

    fun getElvesWeights(input: List<String>): List<Int> {
        return input
            .joinToString("\n")
            .split("\n\n".toRegex())
            .map { it
                .split("\n".toRegex())
                .map(String::toInt)
                .sum()
            }
    }

    fun part1(input: List<String>): Int {
        return getElvesWeights(input).max()
    }

    fun part2(input: List<String>): Int {
        return getElvesWeights(input)
            .sortedDescending()
            .take(3)
            .sum()
    }

    val testInput = readInput("Day${id}_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day${id}")
    println("==== DAY ${id} ====")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
