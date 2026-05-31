package leetcode.set75

import kotlin.math.max

class LongestConsecutiveSequence {

    //Giving TLE as O(n2) in case of a reverse numbers list eg. 100,99,98,97...1
    fun longestConsecutive2(nums: IntArray): Int {

        var maxLen = 1

        val len = nums.size
        val cache = mutableMapOf<Int, Int>()

        for (i in 0 until len) {
            val num = nums[i]

            if (cache.containsKey(num)) continue

            if (!cache.containsKey(num-1) && !cache.containsKey(num+1)) {
                cache[num] = 1
                continue
            }

            val leftCount = cache.getOrDefault(num-1, 0)
            val rightCount = cache.getOrDefault(num+1, 0)
            val newCount = leftCount + rightCount + 1

            cache[num] = newCount
            maxLen = max(maxLen, newCount)
            var cur = num-1
            while(cache.containsKey(cur)) {
                cache[cur] = newCount
                cur--
            }
            cur = num+1
            while(cache.containsKey(cur)) {
                cache[cur] = newCount
                cur++
            }
        }

        return  maxLen
    }

    fun longestConsecutive(nums: IntArray): Int {
        val len = nums.size
        var maxLen = 1
        val cache = mutableSetOf<Int>()

        for (i in 0 until  len) {
            cache.add(nums[i])
        }

        for (i in 0 until  len) {
            if (!cache.contains(nums[i]-1)) {
                var curLen = 0
                while (cache.contains(nums[i]+curLen)) {
                    curLen++
                }
                maxLen = max(maxLen, curLen)
            }
        }
        return maxLen
    }
}

fun main(args: Array<String>) {
    println(LongestConsecutiveSequence().longestConsecutive(intArrayOf(100,4,200,1,3,2)))
    println(LongestConsecutiveSequence().longestConsecutive(intArrayOf(0,3,7,2,5,8,4,6,0,1)))
    println(LongestConsecutiveSequence().longestConsecutive(intArrayOf(1,0,1,2)))

}