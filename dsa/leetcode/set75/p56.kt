package dsa.leetcode.set75.p56

import java.util.Arrays

fun main() {
    println("Merge Intervals")
    val data =
            arrayOf(
                    intArrayOf(1, 3),
                    intArrayOf(2, 6),
                    intArrayOf(15, 18),
                    intArrayOf(8, 10),
            )

    val data2 =
            arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(5, 7),
            )
    val data3 =
            arrayOf(
                    intArrayOf(4, 7),
            )
    val data4 =
            arrayOf(
                    intArrayOf(1, 3),
                    intArrayOf(4, 6),
                    intArrayOf(4, 7),
            )
    println(Arrays.deepToString(Solution().merge(data)))
    println(Arrays.deepToString(Solution().merge(data2)))
    println(Arrays.deepToString(Solution().merge(data3)))
    println(Arrays.deepToString(Solution().merge(data4)))
}

class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val res = ArrayList<IntArray>()

        val size = intervals.size
        if (size == 1) {
            return intervals
        }
        intervals.sortWith(compareBy({ it[0] }, { it[1] }))
        var i = 0

        var lastLeft = intervals[i][0]
        var lastRight = intervals[i][1]
        var shouldAddFirstItem = true // TODO can we do more cleanly for adding first item ?
        i++
        while (i < size) {

            var curLeft = intervals[i][0]
            var curRight = intervals[i][1]
            var areEntriesSkipped = false

            while (i < size && intervals[i][0] <= lastRight) {
                areEntriesSkipped = true
                if (i < size && intervals[i][1] >= curRight) {
                    curLeft = intervals[i][0]
                    curRight = intervals[i][1]
                    if (curRight > lastRight) {
                        lastRight = curRight
                    }
                }
                i++
            }

            if (curLeft <= lastLeft) {
                if (curRight >= lastRight) {
                    lastLeft = curLeft
                    lastRight = curRight
                }
            } else if (curLeft <= lastRight) {
                // there is overlap
                if (curRight > lastRight) {
                    lastRight = curRight
                }
            } else {
                if (shouldAddFirstItem) {
                    res.add(intArrayOf(lastLeft, lastRight))
                }
                lastLeft = curLeft
                lastRight = curRight
            }

            if (!areEntriesSkipped) {
                i++
            }
            if (res.size > 0) {
                val lastAddedItem = res[res.size - 1]
                if (lastAddedItem[0] == lastLeft) {
                    res[res.size - 1][1] = lastRight
                } else {
                    res.add(intArrayOf(lastLeft, lastRight))
                }
            } else {
                res.add(intArrayOf(lastLeft, lastRight))
            }

            shouldAddFirstItem = false
        }

        return res.toTypedArray()
    }
}
