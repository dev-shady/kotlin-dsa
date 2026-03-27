/*
Given an array arr[] of non-negative integers and a value sum, the task is to check
if there is a subset of the given array whose sum is equal to the given sum.
*/

fun main() {
    println("Subset Sum Problem")
    println(subsetSum(intArrayOf(3, 34, 4, 12, 5, 2), 9))
    println(subsetSum(intArrayOf(3, 34, 4, 12, 5, 2), 30))
    println(subsetSum(intArrayOf(1, 5, 3, 7, 4), 12))
}

fun subsetSum(arr: IntArray, sum: Int): Boolean {
    val n = arr.size
    var dp = Array(n + 1) { BooleanArray(sum + 1) }

    dp[0][0] = true

    for (i in 1..n) {
        for (j in 0..sum) {
            if (arr[i - 1] <= j) {
                dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]]
            } else {
                dp[i][j] = dp[i - 1][j]
            }
        }
    }

    return dp[n][sum]
}
