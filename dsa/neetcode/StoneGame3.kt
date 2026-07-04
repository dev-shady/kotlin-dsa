package neetcode

import kotlin.math.max

class StoneGame3 {

    enum class Result {
        Alice,
        Bob,
        Tie
    }

    fun stoneGameIII(stoneValue: IntArray): String {
        var result = Result.Bob.toString()

        val n = stoneValue.size

        var maxDifference = Int.MIN_VALUE
        val cache = IntArray(n) { Int.MIN_VALUE }
        maxDifference = optimalDifference(stoneValue, 0, cache)

        if (maxDifference > 0) {
            return Result.Alice.toString()
        } else if (maxDifference < 0) {
            return Result.Bob.toString()
        } else {
            return Result.Tie.toString()
        }

        return result
    }

    fun optimalDifference(stoneValue: IntArray, idx: Int, cache: IntArray): Int {
        var curSum = 0
        var curDifference = 0
        var maxDifference = Int.MIN_VALUE
        val n = stoneValue.size

        if (idx >= n) {
            return 0
        }

        if (cache[idx] != Int.MIN_VALUE) {
            return cache[idx]
        }

        for (i in 0 until 3) {
            if (idx + i < n) {
                curSum += stoneValue[idx+i]
                curDifference = curSum - optimalDifference(stoneValue, idx+i+1, cache)
                maxDifference = max(maxDifference, curDifference)
            }
        }

        cache[idx] = maxDifference
        return cache[idx]
    }

}

fun main(args: Array<String>) {
    println(StoneGameIII().stoneGameIII(intArrayOf(2,4,3,1)))
    println(StoneGameIII().stoneGameIII(intArrayOf(1,2,1,5)))
    println(StoneGameIII().stoneGameIII(intArrayOf(5,-3,3,5)))
    println(StoneGameIII().stoneGameIII(intArrayOf(-1,-2,-3)))

}