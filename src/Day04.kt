fun main() {
    val id = "04"

    fun part1(input: List<String>): Int {
        return input.count {
            val (a,b,x,y) = it.replace(',', '-').split('-').map(String::toInt)
            a in x..y && b in x..y || x in a..b && y in a..b
        }
    }

    fun part2(input: List<String>): Int {
        return input.count {
            val (a,b,x,y) = it.replace(',', '-').split('-').map(String::toInt)

            a in x..y || b in x..y || x in a..b || y in a..b
        }
    }

    val testInput = readInput("Day${id}_test")
    check(part1(testInput) == 2)

    val input = readInput("Day${id}")
    println("==== DAY $id ====")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
