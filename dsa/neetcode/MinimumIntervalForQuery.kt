package neetcode

import java.util.PriorityQueue

class MinimumIntervalForQuery {
    data class Interval (val first: Int, val second: Int)
    data class QueryEntity (val value: Int, val idx: Int)
    data class PriorityInterval(val weight: Int, val interval: Interval)
    fun minInterval(intervals: Array<IntArray>, queries: IntArray): IntArray {
        val resIntervals = IntArray(queries.size)
        val size =  intervals.size
        val intervalsList = ArrayList<Interval>()
        val queriesList = ArrayList<QueryEntity>()

        for (i in 0 until size) {
            val interval = Interval(intervals[i][0],intervals[i][1])
            intervalsList.add(interval)
        }

        intervalsList.sortWith {a,b -> a.first - b.first}
        val queryLen = queries.size
        for (i in 0 until queryLen) {
            queriesList.add(QueryEntity(queries[i], i))
        }

        queriesList.sortWith {a,b -> a.value - b.value}

        println(intervalsList)
        println(queriesList)

        val minHeap = PriorityQueue<PriorityInterval>(size) {a,b -> a.weight - b.weight}

       var intervalIdx = 0
        var queryIdx = 0
        while (queryIdx < queryLen) {
            val query = queriesList[queryIdx]
           while (intervalIdx < size &&  query.value >= intervalsList[intervalIdx].first) {
                    val interval  = intervalsList[intervalIdx]
                   minHeap.add(PriorityInterval(interval.second - interval.first + 1, interval))
                   intervalIdx++
           }

            if (minHeap.isEmpty()) {
                resIntervals[query.idx] = -1
                queryIdx++
                continue
            }

            var priorityInterval = minHeap.peek()
            while (priorityInterval.interval.second < query.value) {
                minHeap.poll()
                if (minHeap.isEmpty()) break
                priorityInterval = minHeap.peek()
            }

            if (minHeap.isEmpty()) {
                resIntervals[query.idx] = -1
                queryIdx++
            } else {
                resIntervals[query.idx] = priorityInterval.weight
                queryIdx++
            }

        }

        return resIntervals
    }

}

fun main(args: Array<String>) {
    println(MinimumIntervalForQuery().minInterval(arrayOf(intArrayOf(1,3),intArrayOf(2,3),intArrayOf(3,7),intArrayOf(6,6)),
        intArrayOf(2,3,1,7,6,8)).toList())
}