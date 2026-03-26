fun lcs(s1: String, s2: String): Int {

    val len1 = s1.length
    val len2 = s2.length

    var dp = Array(len2) { IntArray(len1) }

    if (s1[0] == s2[0]) {
        dp[0][0] = 1
    } else {
        dp[0][0] = 0
    }

    (1 until len1).forEach { index ->
        if (s1[index] == s2[0]) {
            dp[0][index] = 1
        } else {
            dp[0][index] = dp[0][index - 1]
        }
    }

    (1 until len2).forEach { index ->
        if (s2[index] == s1[0]) {
            dp[index][0] = 1
        } else {
            dp[index][0] = dp[index - 1][0]
        }
    }

    for (i in (1 until len2)) {
        for (j in (1 until len1)) {
            if (s2[i] == s1[j]) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + 1
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    return dp[len2 - 1][len1 - 1]
}

fun main() {
    println(lcs("AGGTAB", "GXTXAYB"))
    println(lcs("ABC", "ACD"))
    println(lcs("ABC", "CBA"))
}
