fun main() {
    val filenameTemplate = "/Users/jayanth.visweswaran/Projects/adventOfCode2020/src/main/data/%d"
    println("Hello World!")
//    val downloader = Downloader()
//    downloader.getFile(6)
    val sol = Ten(filenameTemplate.format(10))
    sol.solve()
}