fun main() {
    println("Min Cuts for Palindorme Partitioning")
    println(palindromePartition("geek"))
    println(palindromePartition("eeee"))
    println(palindromePartition("geeg"))
    println(palindromePartition("geeekeex"))
}

// O(n2) Space and Time Complexity
fun palindromePartition(str: String): Int {
    val n = str.length

    var isPal = Array(n) { BooleanArray(n) }

    var dp = IntArray(n) { n }

    isPalindromePreCalculation(isPal, str)

    dp[0] = 0

    for (i in 1 until n) {

        if (isPal[0][i]) {
            dp[i] = 0
        } else {
            var j = i
            var cur = n
            while (j > 0) {
                if (isPal[j][i]) {
                    cur = Math.min(cur, 1 + dp[j - 1])
                }
                j--
            }
            dp[i] = cur
        }
    }

    return dp[n - 1]
}

fun isPalindromePreCalculation(isPal: Array<BooleanArray>, str: String) {
    val n = str.length

    for (i in 0 until n) {
        isPal[i][i] = true
    }

    for (len in 2..n) {
        var i = 0
        var j = i + len - 1
        while (j < n) {
            isPal[i][j] = (str[i] == str[j]) && (len == 2 || isPal[i + 1][j - 1])
            i++
            j++
        }
    }
}
