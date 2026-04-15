package dsa.leetcode.p1326
fun main() {
    println("Min Taps to Water Garden")
    println(Solution().minTaps(5, intArrayOf(3, 4, 1, 1, 0, 0)))
    println(Solution().minTaps(5, intArrayOf(0, 0, 0, 0)))
}

class Solution {
    fun minTaps(n: Int, ranges: IntArray): Int {
        val size = ranges.size // n+1
        val rangeList = ArrayList<Range>(size)
        repeat(size) {
            val l = if (it - ranges[it] < 0) 0 else it - ranges[it]
            var r = it + ranges[it]
            rangeList.add(Range(l, r))
        }
        rangeList.sortWith(compareBy({ it.l }, { it.r }))

        var tapsCount = 1
        var lastLeft: Int = rangeList[0].l
        var lastRight: Int = rangeList[0].r
        var i = 1
        while (i < size) {

            if (lastRight >= n) {
                return tapsCount
            }

            var curLeft = rangeList[i].l
            var curRight = rangeList[i].r
            var areEntriesSkipped = false

            while (i < size && rangeList[i].l <= lastRight) {
                areEntriesSkipped = true
                if (i < size && rangeList[i].r > curRight) {
                    curLeft = rangeList[i].l
                    curRight = rangeList[i].r
                }

                if (curRight >= n) {
                    break
                }
                if (rangeList[i].l == lastLeft) {
                    lastRight = rangeList[i].r
                }
                i++
            }

            if (curLeft <= lastLeft) {
                // not possible less than as data sorted, will fix
                // solution works but do we really need equality check ?
                if (curRight >= lastRight) {
                    lastLeft = curLeft
                    lastRight = curRight
                }
            } else if (curLeft <= lastRight) {
                // there is overlap
                if (curRight > lastRight && lastRight < n) {
                    tapsCount++
                    lastRight = curRight
                }
            } else {
                return -1
            }
            if (!areEntriesSkipped) {
                i++
            }
        }

        return tapsCount
    }

    class Range(val l: Int, val r: Int)
}
