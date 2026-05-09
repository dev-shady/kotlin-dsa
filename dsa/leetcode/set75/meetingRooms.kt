package leetcode.set75

import java.util.PriorityQueue

class Solution {
    fun minMeetingRooms(intervals: List<Interval>): Int {
        if (intervals.size == 0) return 0
        var result = 0

        val sorted = intervals.sortedWith(Comparator { int1, int2 ->
            if (int1.start == int2.start) {
                int1.end - int2.end
            } else {
                int1.start - int2.start
            }
        })

        println(sorted)

        var rooms = 0
        var maxRooms = 0
        val size = intervals.size
        val queue = PriorityQueue<Int>(sorted.size)

        repeat(size) { i ->
            val item = sorted[i]
            while (queue.isNotEmpty() && queue.peek() <= item.start) {
                queue.poll()
                rooms--
            }
            rooms++
            maxRooms = Math.max(maxRooms, rooms)
            queue.offer(item.end)
        }

        return maxRooms
    }

    data class Interval(var start: Int, var end: Int) {}
}

fun main(args: Array<String>) {
    println(Solution().minMeetingRooms(listOf(
        Solution.Interval(2,3),Solution.Interval(2,4),Solution.Interval(4,6),Solution.Interval(3,7),
        Solution.Interval(1,10),Solution.Interval(9,10),Solution.Interval(3,8))))

    println(Solution().minMeetingRooms(listOf(
        Solution.Interval(1,5), Solution.Interval(15,20), Solution.Interval(5,10), Solution.Interval(10,15),
        Solution.Interval(1,20),Solution.Interval(2,6)
        )))

}