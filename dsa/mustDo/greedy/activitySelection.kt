import java.util.PriorityQueue /*
                               start[] = [1, 3, 0, 5, 8, 5], finish[] = [2, 4, 6, 7, 9, 9]

                               1-2, 3-4,5-7,8-9

                               start[] = [10, 12, 26], finish[] = [20, 25, 30]

                               10-20

                               Choose the activities in order finishing first.
                                   - use sorting and then pick if start > lastFinishTime
                                   - use min heap with (finish, start) and pick if start > lastFinishTime
                               */

class ActivitySelection {

    data class Activity(val startTime: Int, val finishTime: Int) : Comparable<Activity> {

        override fun compareTo(other: Activity): Int = compareValues(finishTime, other.finishTime)
    }

    fun maxActivtiesCount(start: IntArray, finish: IntArray): Int {
        var maxActivities = 0
        val activities = mutableListOf<Activity>()
        for (index in 0 until (start.size)) {
            activities.add(Activity(start[index], finish[index]))
        }

        activities.sort()

        // activities.forEach { activity ->
        //     println("${activity.startTime} till ${activity.finishTime}")
        // }

        var lastFinishTime = 0

        activities.forEach { activity ->
            if (lastFinishTime < activity.startTime) {
                maxActivities++
                lastFinishTime = activity.finishTime
            }
        }

        println("max no of activities: ${maxActivities}")
        return maxActivities
    }

    fun maxActivtiesCountWithPQ(start: IntArray, finish: IntArray): Int {
        var maxActivities = 0
        val minQueue = PriorityQueue<Activity>()

        for (index in 0 until (start.size)) {
            minQueue.offer(Activity(start[index], finish[index]))
        }

        var lastFinishTime = 0
        while (minQueue.isNotEmpty()) {
            val activity = minQueue.poll()
            if (lastFinishTime < activity.startTime) {
                maxActivities++
                lastFinishTime = activity.finishTime
            }
        }

        println("max no of activities (PQ): ${maxActivities}")
        return maxActivities
    }
}

fun main() {
    ActivitySelection()
            .maxActivtiesCountWithPQ(intArrayOf(1, 3, 0, 5, 8, 5), intArrayOf(2, 4, 6, 7, 9, 9))
    ActivitySelection().maxActivtiesCountWithPQ(intArrayOf(10, 12, 20), intArrayOf(20, 25, 30))
}
