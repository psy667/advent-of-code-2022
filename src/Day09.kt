import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.sign

fun main() {
    val id = "09"

    data class Pos(val y: Int, val x: Int) {
        operator fun plus(b: Pos): Pos {
            return Pos(this.y + b.y, this.x + b.x)
        }

        override fun toString(): String {
            return "${this.y} ${this.x}"
        }
    }

    fun parse(input: List<String>): List<Pair<Pos, Int>> {
        return input.map {
            val (dir, timesStr) = it.split(" ")
            val times = timesStr.toInt()
            when(dir) {
                "U" -> Pos(-1, 0)
                "D" -> Pos(1, 0)
                "R" -> Pos(0, 1)
                "L" -> Pos(0, -1)
                else -> Pos(0, 0)
            } to times
        }
    }

    fun moveTail(head: Pos, tail: Pos): Pos {
        val diff = (tail.y - head.y) to (tail.x - head.x)
        if(max(diff.first.absoluteValue, diff.second.absoluteValue) <= 1) {
            return tail
        }
        return Pos(tail.y + diff.first.sign * -1, tail.x + diff.second.sign * -1)
    }

    fun part1(input: List<String>): Int {


        var head = Pos(0, 0)
        var tail = Pos(0, 0)
        val visited = mutableSetOf(Pos(0, 0))


        parse(input).forEach {
            val (vec, times) = it
            repeat(times) {
                head += vec

                tail = moveTail(head, tail)
                visited.add(tail)
            }
        }

        return visited.size
    }

    fun part2(input: List<String>): Int {
        var head = Pos(0, 0)
        val tail = MutableList(9) {_ -> Pos(0, 0)}
        val visited = mutableSetOf(Pos(0, 0))

        parse(input).forEach {
            val (vec, times) = it
            repeat(times) {
                head += vec

                var prev = head

                tail.forEachIndexed{ idx, it ->
                    tail[idx] = moveTail(prev, it)

                    if(idx == tail.lastIndex) {
                        visited.add(tail[idx])
                    }
                    prev = tail[idx]
                }
            }
        }
        return visited.size
    }

    val testInput = readInput("Day${id}_test")
    check(part1(testInput) == 13)

    val input = readInput("Day${id}")
    println("==== DAY $id ====")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
