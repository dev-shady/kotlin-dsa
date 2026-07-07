package neetcode

class RegularExpressionMatching {

    fun isMatch(s: String, p: String): Boolean {

        val m = s.length
        val n = p.length


        val dp = Array(m+1) { IntArray(n+1) {-1} }

        val res = isValid(s, p, dp, 0, 0)
        if (res == 1) {
            return true
        } else {
            return false
        }
    }

    fun isValid(s: String, p: String, dp: Array<IntArray>, sIdx: Int, pIdx: Int): Int {
        val sLen  = s.length
        val tLen  = p.length

        if (pIdx >= tLen) {
            return if (sIdx >= sLen) 1 else 0
        }

        if (dp[sIdx][pIdx] != -1) {
            return dp[sIdx][pIdx]
        }

        val match = if (sIdx < sLen && (s[sIdx] == p[pIdx] || p[pIdx] == '.')) true else false

        if (pIdx+1 < tLen && p[pIdx+1] == '*') {
            val first = isValid(s, p, dp, sIdx, pIdx + 2)  // 0 chars
            val second = if (match) isValid(s, p, dp, sIdx+1, pIdx) else 0 // 1 char
            if (first == 1 || second == 1) {
                dp[sIdx][pIdx] = 1
            } else {
                dp[sIdx][pIdx] = 0
            }
            return dp[sIdx][pIdx]

        } else {
            if (match) {
                dp[sIdx][pIdx] = isValid(s, p, dp, sIdx + 1, pIdx+1)
                return dp[sIdx][pIdx]
            } else {
                dp[sIdx][pIdx] = 0
                return dp[sIdx][pIdx]
            }
        }
    }
}

fun main() {
    println(RegularExpressionMatching().isMatch("xyz", ".*z"))
    println(RegularExpressionMatching().isMatch("aa", ".a"))
    println(RegularExpressionMatching().isMatch("nnn", "n*"))


}