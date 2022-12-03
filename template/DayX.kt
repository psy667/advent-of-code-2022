fun main() {
    val id = "X"

    fun part1(input: List<String>): Int {
        return input.map(String::toInt).map {
            it
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map(String::toInt).map {
            it
        }.sum()
    }

    val testInput = readInput("Day${id}_test")
    check(part1(testInput) == 15)

    val input = readInput("Day${id}")
    println("==== DAY $id ====")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
