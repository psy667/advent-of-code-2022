
fun main() {
    val id = "08"

    fun parse(input: List<String>): List<List<Int>> {
        return input.map {
            it.split("").drop(1).dropLast(1).map(String::toInt)
        }
    }

    fun part1(input: List<String>): Int {
        val treesMatrix = parse(input)
        val size = treesMatrix.size

        val visibilityMatrix = List(size) { MutableList(size) { 0 } }


        listOf(
            0 until size,
            size.dec() downTo 0
        ).forEach {
            val range = it
            for (y in range) {
                var maxInRow = -1
                for (x in range) {
                    if (treesMatrix[y][x] > maxInRow) {
                        visibilityMatrix[y][x] = 1
                        maxInRow = treesMatrix[y][x]
                    }
                }
            }
        }

        listOf(
            0 until size,
            size.dec() downTo 0
        ).forEach {
            val range = it
            for (x in range) {
                var maxInRow = -1
                for (y in range) {
                    if (treesMatrix[y][x] > maxInRow) {
                        visibilityMatrix[y][x] = 1
                        maxInRow = treesMatrix[y][x]
                    }
                }
            }
        }

        return visibilityMatrix.sumOf { it.sum() }
    }

    fun part2(input: List<String>): Int {
        val treesMatrix = parse(input)
        val size = treesMatrix.size

        var maxValue = 0

        for (y in 1 until size-1) {
            for (x in 1 until size-1) {
                var totalRes = 1

                listOf(
                    listOf(1, 0),
                    listOf(-1, 0),
                    listOf(0, 1),
                    listOf(0, -1)
                ).forEach {
                    var res = 0
                    val (mY, mX) = it
                    var (nY, nX) = listOf(y + mY, x + mX)

                    while (nY < size && nX < size && nY >= 0 && nX >= 0) {
                        res++
                        if (treesMatrix[nY][nX] >= treesMatrix[y][x]) {
                            break
                        }

                        nY += mY
                        nX += mX
                    }

                    totalRes *= res
                }
                maxValue = maxOf(maxValue, totalRes)
            }
        }

        return maxValue
    }

    val testInput = readInput("Day${id}_test")

    check(part1(testInput) == 21)
    check(part2(testInput) == 8)


    val input = readInput("Day${id}")
    println("==== DAY $id ====")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
