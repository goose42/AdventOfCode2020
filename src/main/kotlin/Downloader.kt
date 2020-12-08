import java.lang.Exception
import java.net.URL

class Downloader {
    val baseUrl = "https://adventofcode.com/2020/day/%d/input"
    val cookie = "session=%s"
    fun getFile(
        day: Int,
        sessionId: String = "53616c7465645f5f0cd3b7fee7dfc1792f4a62875ca940a1069cf643aa28b590f9484557c731b74e8b6ed08972259488"
    ) {
        val formattedUrl = baseUrl.format(day)
        println("fetching from $formattedUrl")


        val response = try {
            val connection = URL(formattedUrl).openConnection()
            connection.setRequestProperty("Cookie", cookie.format(sessionId))
            connection
                .getInputStream()
                .bufferedReader()
                .use { it.readText() }
        } catch (e: Exception) {
            println(e)
        }
        println(response)
    }
}