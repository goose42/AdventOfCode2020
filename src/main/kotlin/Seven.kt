import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class Seven {
    val fileName = "/Users/jayanth.visweswaran/Projects/adventOfCode2020/src/main/data/7"
    val myBag = "shiny gold"
    fun solve() {
        val bagDict = mutableMapOf<String,List<String>>()
        File(fileName).forEachLine {
            val (key, value) = it.split("contain")
            val listOfBags = mutableListOf<String>()
            for (bag in value.split(',')) {
                if (bag == " no other bags.") {
                    continue
                }
                try {
                    val bagCount = bag.count()
                    for(index in 1..bagCount) {
                        listOfBags.add(bag.bagName())
                    }
                } catch (e: Exception) {
                    print("bag: $bag")
                    println(e.message)
                }
            }
            bagDict[key.substring(0, key.length -6)] = listOfBags
        }

        partA(bagDict)

        partB(bagDict)

    }


    private fun partB(bagDict: Map<String, List<String>>) {
        var directBags = ArrayList<String>()
        var totalCount = 0
        for(bag in bagDict[myBag]!!) {
            directBags.add(bag)
        }
        while (directBags.isNotEmpty()) {
            val bag = directBags.removeFirst()
            totalCount++
            bagDict[bag]?.let {
                for(newBag in it) {
                    directBags.add(newBag)
                }
            }
        }
        println(totalCount)
    }


    private fun partA(bagDict: Map<String, List<String>>) {
        var listOfContainerBags = lookupContainerBags(myBag, bagDict)
        val setOfAllSuperBags = mutableSetOf<String>()
        while(listOfContainerBags.isNotEmpty()) {
            val newBag = listOfContainerBags.removeFirst()
            setOfAllSuperBags.add(newBag)
            for(newerBag in lookupContainerBags(newBag, bagDict)) {
                listOfContainerBags.add(newerBag)
            }
        }
        println(setOfAllSuperBags.size)
    }

    private fun lookupContainerBags(thisBag: String, bagDict: Map<String, List<String>>): ArrayList<String> {
        val containerBags = ArrayList<String>()
        for((key,value) in bagDict) {
            if (value.contains(thisBag)) {
                containerBags.add(key)
            }
        }
        return containerBags
    }

    private fun String.bagName(): String {
        val removeNumber = this.split("bag")[0]
        return removeNumber.substring(3, removeNumber.length -1)
    }

    private fun String.count(): Int {
        return this.substring(1,2).toInt()
    }
}