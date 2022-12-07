enum class Type {
    DIR,
    FILE,
}
data class Node(val type: Type, val name: String, var size: Int, val children: MutableList<Node>)

fun main() {
    val id = "07"



    fun Node.addChildDir(name: String) {
        this.children.add(Node(Type.DIR, name, 0, mutableListOf()))
    }

    fun Node.addChildFile(name: String, size: Int) {
        this.children.add(Node(Type.FILE, name, size, mutableListOf()))
    }

    fun printTree(node: Node, depth: Int = 0) {
        println("${"  ".repeat(depth)} - ${node.name} ${node.type} ${node.size}")
        if(node.type == Type.DIR) {
            node.children.forEach{it -> printTree(it, depth + 1)}
        }
    }

    fun calcSize(node: Node): Int {
        if(node.type == Type.FILE) {
            return node.size
        }
        node.size = node.children.map{ calcSize(it) }.sum()
        return node.size
    }

    fun flatTree(node: Node): MutableList<Node> {
        val list = mutableListOf(node)
        list.addAll(node.children.flatMap { flatTree(it) })
        return list
    }

    fun part1(input: List<String>): Int {
        val path = mutableListOf<String>()
        val tree = Node(Type.DIR, "/", 0, mutableListOf())

        fun Node.getByPath(path: List<String>): Node {
            var res = tree

            path.drop(1).forEach { name ->
                res = res.children.find{ it.name == name }!!
            }

            return res
        }

        input.forEach {
            if (it.startsWith("$")) {
                val (_, cmd) = it.split(" ")

                if (cmd == "cd") {
                    val name = it.split(" ")[2]

                    if (name == "..") {
                        path.removeLast()
                    } else {
                        path.add(name)
                    }
                }
            } else {
                val (dirOrSize, name) = it.split(" ")

                if (dirOrSize == "dir") {
                    tree.getByPath(path).addChildDir(name)
                } else {
                    tree.getByPath(path).addChildFile(name, dirOrSize.toInt())
                }
            }
        }

        calcSize(tree)

        return flatTree(tree)
            .filter {it.type != Type.FILE}
            .filter{ it.size <= 100000 }
            .map{ it.size }
            .sum()
    }

    fun part2(input: List<String>): Int {
        val path = mutableListOf<String>()
        val tree = Node(Type.DIR, "/", 0, mutableListOf())

        fun Node.getByPath(path: List<String>): Node {
            var res = tree

            path.drop(1).forEach { name ->
                res = res.children.find{ it.name == name }!!
            }

            return res
        }

        input.forEach {
            if (it.startsWith("$")) {
                val (_, cmd) = it.split(" ")

                if (cmd == "cd") {
                    val name = it.split(" ")[2]

                    if (name == "..") {
                        path.removeLast()
                    } else {
                        path.add(name)
                    }
                }
            } else {
                val (dirOrSize, name) = it.split(" ")

                if (dirOrSize == "dir") {
                    tree.getByPath(path).addChildDir(name)
                } else {
                    tree.getByPath(path).addChildFile(name, dirOrSize.toInt())
                }
            }
        }

        calcSize(tree)

        val used = tree.size

        val remain = 70000000L - used

        return flatTree(tree)
            .filter {it.type != Type.FILE}
            .sortedBy { it.size }
            .first { it.size + remain >= 30000000 }
            .size

    }

    val testInput = readInput("Day${id}_test")
    check(part2(testInput) == 24933642)

    val input = readInput("Day${id}")
    println("==== DAY $id ====")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
