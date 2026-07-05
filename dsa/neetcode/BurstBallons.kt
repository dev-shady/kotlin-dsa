package neetcode

import kotlin.math.max

class BurstBallons {
    fun maxCoins(nums: IntArray): Int {

        val n = nums.size
        val cache = Array(n+1) { IntArray(n+1) { -1 } }

        val paddedNums = IntArray(n+2)
        paddedNums[0] = 1
        paddedNums[n+1] = 1
        for (i in 0 until n) {
            paddedNums[i+1] = nums[i]
        }

        return maxCoinsPossible(paddedNums, cache, 1, n)
    }

    fun maxCoinsPossible(nums: IntArray, cache: Array<IntArray>, l: Int, r: Int): Int {

        if (l>r) return 0

        if (cache[l][r] != -1) return cache[l][r]


        var maxCoins = 0
        var curCoins = 0

        for (i in l..r) {
            // if ith is the last ballon to burst
            curCoins = (nums[l-1]*nums[i]*nums[r+1]) + maxCoinsPossible(nums, cache, l, i-1) +maxCoinsPossible(nums, cache, i+1, r)
            maxCoins = max(maxCoins, curCoins)
        }

        cache[l][r] = maxCoins
        return maxCoins
    }
}

fun main(args: Array<String>) {
    println(BurstBallons().maxCoins(intArrayOf(4,2,3,7)))
}