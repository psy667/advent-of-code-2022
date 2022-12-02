enum class Fig(val score: Int) {
    Rock(1), Paper(2), Scissors(3)
}

enum class Outcome(val score: Int) {
    Lose(0), Draw(3), Win(6)
}

fun main() {
    val id = "02"

    fun String.toFig() = when(this) {
        "A", "X" -> Fig.Rock
        "B", "Y" -> Fig.Paper
        "C", "Z" -> Fig.Scissors
        else -> throw Error()
    }

    fun loserTo(it: Fig) = when(it) {
        Fig.Rock -> Fig.Scissors
        Fig.Scissors -> Fig.Paper
        Fig.Paper -> Fig.Rock
    }

    fun getOutcome(me: Fig, op: Fig) = when {
        me == op -> Outcome.Draw
        loserTo(me) == op -> Outcome.Win
        else -> Outcome.Lose
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {
            val (op, me) = it.split(" ").map(String::toFig)
            getOutcome(me, op).score + me.score
        }
    }

    fun String.toOutcome() = when(this) {
        "X" -> Outcome.Lose
        "Y" -> Outcome.Draw
        "Z" -> Outcome.Win
        else -> throw Error()
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.split(" ") }.sumOf {
                val op = it[0].toFig()
                val outcome = it[1].toOutcome()

                val me = Fig
                    .values()
                    .find { me -> getOutcome(me, op) == outcome }!!

                me.score + outcome.score
            }
    }

    val testInput = readInput("Day${id}_test")
    check(part1(testInput) == 15)

    val input = readInput("Day${id}")
    println("==== DAY $id ====")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
