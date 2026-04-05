fun main() {
    // n: eggs available
    // k: total floors
    // return min moves required
    println("Egg Dropping Puzzle Solution 1")
    println(eggDropMinMoves(2, 10))
    println(eggDropMinMoves(2, 36))

    println("Egg Dropping Puzzle Solution 2")
    println(eggDropMinMoves2(2, 10))
    println(eggDropMinMoves2(2, 36))
}

fun eggDropMinMoves(n: Int, k: Int): Int {

    var dp = Array(k + 1) { IntArray(n + 1) }
    var curMoves = 0

    while (dp[curMoves][n] < k) {
        curMoves++
        for (i in 1..n) {
            dp[curMoves][i] = 1 + dp[curMoves - 1][i - 1] + dp[curMoves - 1][i]
        }
    }

    return curMoves
}

// Solution2 broken, will fix later. First solution working.
fun eggDropMinMoves2(n: Int, k: Int): Int {
    var dp = Array(k + 1) { IntArray(n + 1) }

    for (i in 1..k) {
        dp[i][1] = i
    }

    for (i in 1..n) {
        dp[1][i] = 1
    }

    for (i in 1..k) {
        for (j in 1..n) {
            dp[i][j] = 1 + Math.max(dp[i - 1][j - 1], dp[k - i][j])
        }
    }

    return dp[k][n]
}
