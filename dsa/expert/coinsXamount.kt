/** Case 1: Order is NOT important (Combinations) 1 + 2 and 2 + 1 are treated as the SAME way. */
fun countWaysSame(amount: Int, coins: IntArray): Int {
    val dp = IntArray(amount + 1)
    dp[0] = 1

    for (coin in coins) {
        for (i in coin..amount) {
            dp[i] += dp[i - coin]
        }
    }
    return dp[amount]
}

/** Case 2: Order IS important (Permutations) 1 + 2 and 2 + 1 are treated as DIFFERENT ways. */
fun countWaysDifferent(amount: Int, coins: IntArray): Int {
    val dp = IntArray(amount + 1)
    dp[0] = 1

    for (i in 1..amount) {
        for (coin in coins) {
            if (i >= coin) {
                dp[i] += dp[i - coin]
            }
        }
    }
    return dp[amount]
}
