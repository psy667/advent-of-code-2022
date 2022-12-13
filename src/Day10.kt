enum class OpCode {
    ADDX,
    NOOP
}

data class Instruction(val op: OpCode, val arg: Int? = null)


fun main() {
    val id = "10"

    fun parse(input: List<String>): List<Instruction> {
        return input.map {
            val a = it.split(" ")

            when(a.first()) {
                "addx" -> Instruction(OpCode.ADDX, a[1].toInt())
                else -> Instruction(OpCode.NOOP)
            }
        }
    }

    fun part1(input: List<String>): Int {
        var clk = 1
        var str = 1
        var reg = 1
        var block = false
        val insturctions = parse(input).toMutableList()
        var curArg = 0

        while(insturctions.isNotEmpty()) {
//            println(insturctions.size)
//            Thread.sleep(500)
            if((clk - 20) % 40 == 0) {
                str += clk * reg
            //                    println("$clk: reg: $reg str: ${clk * reg}")

            }
            println("$clk: reg: $reg cur str: ${clk * reg} res $str")


            clk += 1

            if(block) {
                block = false
                reg+=curArg
                curArg = 0
                continue
            }
            val it = insturctions.removeFirst()
            println(it)

            if(it.op == OpCode.ADDX) {
                curArg = it.arg!!
                block = true

            } else {
                block = false
            }

        }

        return str.dec()
    }

    fun part2(input: List<String>): Int {
        var clk = 0
        var reg = 1
        var block = false
        val insturctions = parse(input).toMutableList()
        var curArg = 0
        var curLine = MutableList(40) {'.'}

        while(insturctions.isNotEmpty()) {
//            print("${reg.toString().padStart(2, '0')} ")
//            print("${(clk % 40).toString().padStart(2, '0')} ")
//            println("###".padStart(reg - 1 + 3, '.').padEnd(40, '.'))
//            println(reg)
            if((clk % 40) in reg.dec()..reg.inc()) {
                curLine[clk % 40] = '#'
            }

            clk += 1
            if(clk % 40 == 0) {
                println(curLine.joinToString(""))
    curLine = MutableList(40) {'.'}
            }

            if(block) {
                block = false

                reg+=curArg
                curArg = 0
                continue
            }
            val it = insturctions.removeFirst()

            if(it.op == OpCode.ADDX) {
                curArg = it.arg!!
                block = true

            } else {
                block = false
            }


        }

        return 0
    }

    val testInput = readInput("Day${id}_test")
    check(part1(testInput) == 13140)

    val input = readInput("Day${id}")
    println("==== DAY $id ====")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
