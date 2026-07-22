package neetcode

class NQueens2 {

    lateinit var cols: BooleanArray
    lateinit var posDiagonal: BooleanArray
    lateinit var negDiagonal: BooleanArray
    var N = -1
    var count = 0

    fun totalNQueens(n: Int): Int {

        count = 0
        cols = BooleanArray(n)
        posDiagonal = BooleanArray(2*n)
        negDiagonal = BooleanArray(2*n)
        N = n
        countNQueens(0)
        return count
    }

    private fun countNQueens(cur: Int) {
        if (cur == N) {
            count++
            return
        }

        for (j in 0 until N) {
            if ( cols[j] || posDiagonal[cur+j] || negDiagonal[cur - j + N]) {
                continue
            }
            cols[j] = true
            posDiagonal[cur+j] = true
            negDiagonal[cur - j + N] = true
            countNQueens(cur+1)
            cols[j] = false
            posDiagonal[cur+j] = false
            negDiagonal[cur - j + N] = false
        }
    }
}

fun main() {
    println(NQueens2().totalNQueens(1))
    println(NQueens2().totalNQueens(4))

}