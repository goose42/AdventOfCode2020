import java.io.File

class Eleven (private val fileName: String) {
    fun solve() {
        var layout = ArrayList<CharArray>()
        File(fileName).forEachLine {
            try {
                layout.add(it.toCharArray())
            } catch (e: Exception) {
                print(e.message)
            }
        }
        var newLayout = parseLayout(layout)


        for(i in 1..100){
            newLayout = parseLayout(newLayout)
            println( occupiedSeats(newLayout))
        }

        printLayout(newLayout)
        println(occupiedSeats(newLayout))
    }

    private fun parseLayout(layout: ArrayList<CharArray>): ArrayList<CharArray> {

        val newLayout = ArrayList<CharArray>()
        val directions = listOf(
            listOf(0,1),
            listOf(0,-1),
            listOf(1,0),
            listOf(-1,0),
            listOf(1,1),
            listOf(-1,1),
            listOf(1,-1),
            listOf(-1,-1),
        )

        for((i, row) in layout.withIndex()) {
            val newRow = CharArray(row.size)
            for((j, letter) in row.withIndex()) {

                var seatedNeighbours = 0
                for(direction in directions) {
                    var foundSeat = false
                    var newI = i + direction[0]
                    var newJ = j + direction[1]
                    while((!foundSeat) and (isInBounds(layout, newI, newJ))) {
                        if (layout[newI][newJ] == '#') {
                            seatedNeighbours++
                            foundSeat= true
                        }
                        if (layout[newI][newJ] == 'L') {
                            foundSeat=true
                        }
                        newI = newI + direction[0]
                        newJ = newJ + direction[1]
                    }
                }


                if(letter == '.') {
                    newRow[j] = '.'
                    continue
                }

                if ((letter == 'L') and (seatedNeighbours == 0 )) {
                    newRow[j] = '#'
                } else if ((letter == '#') and (seatedNeighbours > 4 )) {
                    newRow[j] = 'L'
                } else {
                    newRow[j] = letter
                }
            }
            newLayout.add(newRow)
        }
        return newLayout
    }

    private fun occupiedSeats(layout: ArrayList<CharArray>): Int {
        var count = 0
        for(row in layout) {
            for (seat in row) {
                if (seat == '#') {count++}
            }
        }
        return count
    }

    private fun printLayout(rowsOfPotions: ArrayList<CharArray>) {
        for(row in rowsOfPotions) {
            println(row)
        }
        println("******************************************************************")
    }

    private fun isInBounds(matrix: ArrayList<CharArray>, i: Int, j: Int): Boolean {
        return ((i < matrix.size) and (i >= 0)  and (j >=0) and (j < matrix[0].size))
    }
}