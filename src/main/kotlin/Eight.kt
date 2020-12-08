import java.io.File

class Eight {
    val fileName = "/Users/jayanth.visweswaran/Projects/adventOfCode2020/src/main/data/8"

    fun solve() {
        val instructions = ArrayList<String>()
        File(fileName).forEachLine {
            instructions.add(it)
        }
        var accum = 0
        var i = 0
        var travelled = mutableListOf<Int>()
        var swapLineNumber = 0
        while(i < instructions.size) {

            if(travelled.contains(i)) {
                println("LOOP $swapLineNumber")
//                readLine()
                swapLineNumber++
                i = 0
                travelled = mutableListOf()
                accum = 0
                continue
            } else {
                travelled.add(i)
            }
            val instruction = instructions[i++]
            val (op,value) = instruction.split(" ")

            try {
                val intVal = value.toInt()
                if (i == swapLineNumber) {
                    when(op) {
                        "acc" -> accum += intVal
                        "nop" -> {
                            println("swap index: $i, Acc: $accum ; action: $op ; count: $value")
                            i += intVal - 1
                        }
                        "jmp" -> println("swap index: $i, Acc: $accum ; action: $op ; count: $value")
                    }
                } else {
                    when(op) {
                        "acc" -> accum += intVal
                        "jmp" -> {
                            println("index: $i, Acc: $accum ; action: $op ; count: $value")
                            i += intVal - 1
                        }
                        "nop" -> println("index: $i, Acc: $accum ; action: $op ; count: $value")
                    }
                }
            } catch (e: Exception) {
                print(e.message)
            }

        }
        println("Acc: $accum")
    }
}