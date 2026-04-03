fun main() {
    println("Buy Sell Stock with Transaction Fee")
    println(buySellStocks(intArrayOf(6, 1, 7, 2, 8, 4), 2))
    println(buySellStocks(intArrayOf(7, 1, 5, 3, 6, 4), 1))
}

fun buySellStocks(ar: IntArray, fee: Int): Int {
    val n = ar.size
    var buy = IntArray(n) { 0 }
    var sell = IntArray(n) { 0 }

    for (i in 1 until n) {
        buy[i] = Math.max(buy[i - 1], sell[i - 1])
        var profit = 0
        for (j in 0 until i) {
            if (ar[i] > ar[j]) {
                profit = Math.max(profit, (ar[i] - ar[j] - fee) + buy[j])
            }
        }
        sell[i] = profit
    }

    return Math.max(buy[n - 1], sell[n - 1])
}
