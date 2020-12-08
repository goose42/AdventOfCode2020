import java.io.File
import java.lang.Exception

class Six {
    val fileName = "/Users/jayanth.visweswaran/Projects/adventOfCode2020/src/main/data/6"

    fun solve() {
        val groupValue = IntArray(26)
        var count = 0
        var groupSize = 0
        try {

            File(fileName).forEachLine {
                if(!it.contentEquals("")) {
                    val individualCustoms = it
                    for(char in individualCustoms.toCharArray()) {
                        groupValue[char - 'a'] += 1
                    }
                    groupSize++
                } else {
                    println("New group")
                    for((index, value) in groupValue.withIndex()) {
                        if((value > 0) and (value == groupSize)) {
                            count++
                        }
                        groupValue[index] = 0

                    }
                    groupSize = 0
                    println(count)
                }

            }
        } catch (e: Exception) {
            print(e.message)
        }

        println(count)





    }
}