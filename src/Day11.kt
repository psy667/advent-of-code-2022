fun plus(a: Long, b: String): Long {
    return if (b == "old") {
        a + a
    } else {
        a + b.toLong()
    }
}

fun prod(a: Long, b: String): Long {
    return if (b == "old") {
        a * a
    } else {
        a * b.toLong()
    }
}

data class Monkey(val id: Long, var items: MutableList<Long>, val op: (a: Long, b: String) -> Long, val opArg: String, val divBy: Long, val ifTrue: Long, val ifFalse: Long, var inspectedItems: Long)

fun main() {
    val id = "11"

    fun parse(input: List<String>): List<Monkey> {
        return input.joinToString("\n").split("\n\n").map {

            val d = it.split("\n")

            val id = d[0].substring(7, 8).toLong()
            val items = d[1].substring(18).split(", ").map(String::toLong).toMutableList()
            val op = d[2][23]

            val opFn = if (op == '+') ::plus else ::prod
            val opArg = d[2].substring(25)

            val div = d[3].substring(21).toLong()
            val ifT = d[4].substring(29).toLong()
            val ifF = d[5].substring(30).toLong()

            Monkey(id,items,opFn,opArg,div,ifT,ifF,0)
        }
    }

    fun part1(input: List<String>): Long {
        val monkeys = parse(input)

        repeat(20) {
            monkeys.forEach { monkey ->
                monkey.items.forEach { item ->
                    monkey.inspectedItems++
                    val new = monkey.op(item, monkey.opArg)
                    val newDiv = new / 3

                    if (newDiv % monkey.divBy == 0L) {
                        monkeys.find { it.id == monkey.ifTrue }!!.items.add(newDiv)
                    } else {
                        monkeys.find { it.id == monkey.ifFalse }!!.items.add(newDiv)
                    }
                }
                monkey.items = mutableListOf()
            }

        }
        return monkeys
            .map { it.inspectedItems }
            .sortedDescending()
            .take(2)
            .reduce { acc, cur -> acc * cur }
    }

    fun part2(input: List<String>): Long {
        val monkeys = parse(input)
        val div = monkeys.map{it.divBy}.reduce { acc, cur -> acc * cur }
        repeat(10000) {
            monkeys.forEach { monkey ->
                monkey.items.forEach { item ->
                    monkey.inspectedItems++
                    val new = monkey.op(item, monkey.opArg)
                    val newDiv = new % div

                    if (newDiv % monkey.divBy == 0L) {
                        val m = monkeys.find { it.id == monkey.ifTrue }!!
                        m.items.add(newDiv)
                    } else {
                        val m = monkeys.find { it.id == monkey.ifFalse }!!
                        m.items.add(newDiv)
                    }
                }
                monkey.items = mutableListOf()
            }
        }
        return monkeys
            .map { it.inspectedItems }
            .sortedDescending()
            .take(2)
            .reduce { acc, cur -> acc * cur }
    }

    val testInput = readInput("Day${id}_test")
    check(part2(testInput) == 2713310158L)

    val input = readInput("Day${id}")
    println("==== DAY $id ====")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
