import java.io.File

class Ten (private val fileName: String) {
    fun solve() {
        val adapters = ArrayList<Int>()
        File(fileName).forEachLine {
            try { adapters.add(it.toInt()) } catch (e: Exception) { print(e.message)}
        }
        adapters.sort()
        var outputVoltage = 0
        val cache = hashMapOf<Int, Long>()
        println(adapterJoining(adapters, 0, cache))

    }

    private fun adapterJoining(adapters: ArrayList<Int>, outputVoltage: Int, cache: HashMap<Int, Long>): Long {
        if (cache.containsKey(outputVoltage)) {
            return cache.get(outputVoltage)!!
        }
        val cutDownList = adapters.filter {
            supportsInput(outputVoltage, it)
        }.sorted()

        if(cutDownList.isEmpty()) {
            return 1L
        }
        var ways = 0L
        for(item in cutDownList) {
            println("$item, $outputVoltage")
            ways += adapterJoining(adapters, item, cache)
        }
        cache[outputVoltage] = ways
        return  ways
    }



    private fun supportsInput(currentOutput: Int, adapterValue: Int): Boolean {
        return (adapterValue == currentOutput + 1) or
            (adapterValue ==  currentOutput + 2) or
            (adapterValue == currentOutput + 3)
    }

}