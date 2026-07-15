package neetcode

class NQueens {

    private lateinit var col: BooleanArray
    private lateinit var posDiagonal: BooleanArray
    private lateinit var negDiagonal: BooleanArray
    val result = ArrayList<ArrayList<String>>()

    fun solveNQueens(n: Int): List<List<String>> {

        result.clear()
        col = BooleanArray(n)
        posDiagonal = BooleanArray(2 * n)
        negDiagonal = BooleanArray(2 * n)

        val output = ArrayDeque<Int>() // insert column index for each queen placement

        // 1st queen placemnt
        canPlaceQueen( output, 0, n)
        return result
    }

    fun canPlaceQueen(output: ArrayDeque<Int>, queenNo: Int, n: Int) {

        if (queenNo == n) {
            //build the combination ".Q..","...Q","Q...","..Q." and add to result
            val currentBoard = ArrayList<String>()
            for (queenCol in output) {
                val rowBuilder = StringBuilder()
                for (c in 0 until n) {
                    if (c == queenCol) rowBuilder.append('Q') else rowBuilder.append('.')
                }
                currentBoard.add(rowBuilder.toString())
            }
            result.add(currentBoard)
            return
        }

        for (j in 0 until n) {
            if (col[j] || posDiagonal[queenNo+j] || negDiagonal[queenNo-j+n]) {
                continue
            }

            //place the queen on (queenNo,j)
            col[j] = true
            posDiagonal[queenNo+j] = true
            negDiagonal[queenNo-j+n] = true
            output.addLast(j)
            canPlaceQueen( output, queenNo+1, n)
            output.removeLast()
            col[j] = false
            posDiagonal[queenNo+j] = false
            negDiagonal[queenNo-j+n] = false
        }
    }
}

fun main() {

    println(NQueens().solveNQueens(4))

}