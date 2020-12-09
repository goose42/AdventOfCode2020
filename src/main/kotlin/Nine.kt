import java.io.File

class Nine(private val fileName: String) {
    fun solve() {
        val numbers = ArrayList<Long>()
        File(fileName).forEachLine {
            try {
                numbers.add(it.toLong())
            } catch (e: Exception) {
                print(e.message)
            }
        }
        val totalShouldEqual = 375054920L


        var found = false
        var start = 0
        while((!found ) or (start == 900)) {
            var acc = 0L
            for(index in start until numbers.size) {
                val number = numbers[index]
                acc += number
                if (acc == totalShouldEqual) {
                    print("yay, start = $start index =$index start=${numbers[start]} end=${numbers[index]} sum = " +
                        "${numbers[start] + 
                        numbers[index]}")
                    val sublist = numbers.subList(start, index)
                    sublist.sort()
                    println("its ${sublist.first() + sublist.last()}")
                    found = true
                    break
                }
                if (acc > totalShouldEqual ){
//                    println("we stopped at $index, with a total of $acc and a diff of ${acc - totalShouldEqual}")
                    found = false
                    start++
                    break
                }

            }
        }

    }

    //5729039

    fun partA(numbers: ArrayList<Long>) {
        for(i in 25 until numbers.size) {
            val start = i - 25
            val end = i
            val currentNumber = numbers[i]
            var foundPair = false
            val sublist = numbers.subList(start, end)
            for(x in sublist) {
                val y = currentNumber - x
                if ((x != y) and sublist.contains(y)) {
                    foundPair = true
                }
            }
            if (!foundPair) {
                println("Not found for $currentNumber at line $i")
            }
        }
    }
}