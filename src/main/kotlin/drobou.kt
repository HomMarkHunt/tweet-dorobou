import twitter4j.Paging
import twitter4j.TwitterFactory
import java.io.File

fun main(args: Array<String>) {
    args.toList().parallelStream().forEach { userId ->
        println(userId)
        val pattern = "\n".toRegex()

        val twitter = TwitterFactory().instance

        File("$userId.txt").printWriter().use { out ->
            (1..16).forEach { idx ->
                val paging = Paging(idx, 200)
                val timeLine = twitter.getUserTimeline(userId.toLong(), paging)
                timeLine.map { it.text.replace(pattern, " ") }.forEach { out.println(it) }
            }
        }
    }
}
