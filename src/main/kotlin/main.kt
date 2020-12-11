fun main() {
    val filenameTemplate = "/Users/jayanth.visweswaran/Projects/adventOfCode2020/src/main/data/%d"
    println("Hello World!")
    val sol = Eleven(filenameTemplate.format(11))
    sol.solve()
}