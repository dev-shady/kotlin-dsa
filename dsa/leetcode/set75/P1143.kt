package leetcode.set75

import kotlin.math.max

class P1143 {
    fun longestCommonSubsequence(text1: String, text2: String): Int {

        val m = text1.length
        val n = text2.length
        val cache = Array(m + 1) { IntArray(n + 1) }

        for (i in 0..n) {
            cache[0][i] = 0
        }
        for (i in 0..m) {
            cache[m][0] = 0
        }

        for (i in 1..m) {
            for (j in 1..n) {
                if (text1[i - 1] == text2[j - 1]) {
                    cache[i][j] =  cache[i - 1][j - 1] + 1
                } else {
                    cache[i][j] = max(cache[i - 1][j], cache[i][j - 1])
                }
            }
        }

        return cache[m][n]
    }
}

fun main() {
    println(P1143().longestCommonSubsequence("leetcode", "leetcode"))
    println(P1143().longestCommonSubsequence("abcde", "ace"))
    println(P1143().longestCommonSubsequence("abc", "def"))

}