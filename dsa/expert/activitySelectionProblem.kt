import java.util.PriorityQueue

fun activitySelection(start: IntArray, finish: IntArray): Int {
    val n = start.size
    var ans = 0

    // Min Heap to store activities in ascending order of finish time
    // We use compareBy { it[0] } to sort by the first element of the intArray
    val p = PriorityQueue<IntArray>(compareBy { it[0] })

    for (i in 0 until n) {
        // Storing as [finishTime, startTime]
        p.add(intArrayOf(finish[i], start[i]))
    }

    // To store the end time of the last selected activity
    var finishTime = -1

    while (p.isNotEmpty()) {
        val activity = p.poll() ?: continue

        // activity[1] is the start time, activity[0] is the finish time
        if (activity[1] > finishTime) {
            finishTime = activity[0]
            ans++
        }
    }

    return ans
}

fun main() {
    val start = intArrayOf(1, 3, 0, 5, 8, 5)
    val finish = intArrayOf(2, 4, 6, 7, 9, 9)

    println(activitySelection(start, finish))
}
