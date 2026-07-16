package leetcode

import java.lang.Math.pow
import kotlin.math.max

class p1537 {

    val LIMIT = 1_000_000_007
    val firstMap = HashMap<Int, Int>()
    val secondMap = HashMap<Int, Int>()
    lateinit var cache1: LongArray
    lateinit var cache2: LongArray

    fun maxSum(nums1: IntArray, nums2: IntArray): Int {

        firstMap.clear()
        secondMap.clear()

        for (i in nums1.indices) {
            firstMap[nums1[i]] = i
        }

        for (i in nums2.indices) {
            secondMap[nums2[i]] = i
        }

        cache1 = LongArray(nums1.size) { -1L }
        cache2 = LongArray(nums2.size) { -1L }

        val maxScore = max(maxOf(nums1, nums2, 0, -1), maxOf(nums1, nums2, -1, 0))
        return (maxScore % LIMIT).toInt()
    }

    fun maxOf(nums1: IntArray, nums2: IntArray, i: Int, j: Int): Long {
        if (i >= nums1.size || j >= nums2.size) {
            return 0
        }



        if (i == -1) {
            if (cache2[j] != -1L) return  cache2[j]

            if (firstMap.containsKey(nums2[j])) {
                val idx = firstMap[nums2[j]]!!
                var sum1 = nums2[j] + maxOf(nums1, nums2, i, j+1)
                var sum2 = nums2[j] + maxOf(nums1, nums2, idx+1, -1)
                cache2[j] = max(sum1, sum2)
                return cache2[j]
            } else {
                var sum1 = nums2[j] + maxOf(nums1, nums2, i, j + 1)
                cache2[j] = sum1
                return cache2[j]
            }
        } else {
            if (cache1[i] != -1L) return  cache1[i]
            if (secondMap.containsKey(nums1[i])) {
                val idx = secondMap[nums1[i]]!!
                var sum1 = nums1[i] + maxOf(nums1, nums2, i+1, j)
                var sum2 = nums1[i] + maxOf(nums1, nums2, -1, idx+1)
                cache1[i] = max(sum1, sum2)
                return cache1[i]
            } else {
                var sum1 = nums1[i] + maxOf(nums1, nums2, i+1, j)
                cache1[i] = sum1
                return cache1[i]
            }
        }
    }
}

fun main() {
    println(p1537().maxSum(intArrayOf(2,4,5,8,10), intArrayOf(4,6,8,9)))
    println(p1537().maxSum(intArrayOf(1,2,3,4,5), intArrayOf(6,7,8,9,10)))

}