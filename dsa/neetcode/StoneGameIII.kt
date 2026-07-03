package neetcode

import kotlin.math.max

class StoneGameIII {

    enum class Result {
        Alice,
        Bob,
        Tie
    }

    fun stoneGameIII(stoneValue: IntArray): String {
        var result = Result.Bob.toString()

        val n = stoneValue.size
        val sumValue = IntArray(n)
        sumValue[0] = stoneValue[0]
        for (i in 1 until n) {
            sumValue[i] = sumValue[i - 1] + stoneValue[i]
        }

        var curSum = 0
        var maxSum = Int.MIN_VALUE
        val cache = IntArray(n) { Int.MIN_VALUE }
        for (i in 0 until 3) {
            if (i < n) {
                curSum = sumValue[i] + nextOptimalSum(stoneValue, sumValue, i+1, cache)
                maxSum = max(maxSum, curSum)
            }
        }

        val opponentSum = sumValue[n-1] - maxSum
        if (maxSum > opponentSum) {
            return Result.Alice.toString()
        } else if (maxSum < opponentSum) {
            return Result.Bob.toString()
        } else {
            return Result.Tie.toString()
        }

        return result
    }

    fun nextOptimalSum(stoneValue: IntArray, sumValue: IntArray, idx: Int, cache: IntArray): Int {
        var curSum = 0
        var maxSum = Int.MIN_VALUE
        val n = stoneValue.size

        if (idx >= n) {
            return 0
        }

        if (cache[idx] != Int.MIN_VALUE) {
            return cache[idx]
        }

        for (i in 0 until 3) {
            if (idx + i < n) {
                curSum = sumValue[idx + i] - sumValue[idx-1] + nextOptimalSum(stoneValue, sumValue, idx+i+1, cache)
                maxSum = max(maxSum, curSum)
            }
        }

//      println("idx end = $idx maxSum: $maxSum totalValuePossible: ${sumValue[n-1] - sumValue[idx-1]}")
        cache[idx] = sumValue[n-1] - sumValue[idx-1] - maxSum
        return cache[idx]
    }
}
fun main(args: Array<String>) {
    println(StoneGameIII().stoneGameIII(intArrayOf(2,4,3,1)))
    println(StoneGameIII().stoneGameIII(intArrayOf(1,2,1,5)))
    println(StoneGameIII().stoneGameIII(intArrayOf(-1,-2,-3)))

}