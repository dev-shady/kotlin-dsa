package neetcode

import kotlin.math.max
import kotlin.math.min

class SplitArrayLargestSum {
    fun splitArray(nums: IntArray, k: Int): Int {

        var low = 0
        var high = 0
        for (i in 0 until nums.size) {
            low = max(low, nums[i])
            high += nums[i]
        }

        var mid = 0
        var answer = Int.MAX_VALUE

        while (low <= high) {
            mid = (low + high)/2
            if (isSplitPossbile(nums, mid, k)) {
                answer = min(answer, mid)
                high = mid-1
            } else {
                low = mid + 1
            }
        }

        return answer
    }

    fun isSplitPossbile(nums: IntArray, target: Int, k: Int): Boolean {
        var groups = 0
        var curSum = 0
        for (i in 0 until nums.size) {

            if (curSum + nums[i] > target) {
                groups++
                if (groups == k) {
                    return false
                }
                curSum = nums[i]
            } else {
                curSum += nums[i]
            }
        }

        return true
    }
}

fun main() {
    println(SplitArrayLargestSum().splitArray(intArrayOf(2,4,10,1,5), 2))
    println(SplitArrayLargestSum().splitArray(intArrayOf(1,0,2,3,5), 2))

}