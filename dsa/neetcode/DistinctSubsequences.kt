package neetcode

class DistinctSubsequences {
    fun numDistinct(s: String, t: String): Int {

        val m = s.length
        val n = t.length
        val dp = Array(m + 1) { IntArray(n + 1) }

        for (i in 0 .. m) {
            dp[i][n] = 1
        }

        for (j in 0 until n) {
            dp[m][j] = 0
        }

        for (j in n-1 downTo 0) {
            for ( i in m-1 downTo 0) {
                if (s[i] == t[j]) {
                    dp[i][j] = dp[i+1][j] + dp[i+1][j+1]
                } else {
                    dp[i][j] = dp[i+1][j]
                }
            }
        }

        return dp[0][0]
    }
}

fun main(args: Array<String>) {
    println(DistinctSubsequences().numDistinct("caaat","cat"))
    println(DistinctSubsequences().numDistinct("xxyxy","xy"))
}
