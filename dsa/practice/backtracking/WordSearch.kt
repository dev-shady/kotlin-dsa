package practice.backtracking

class WordSearch {
    val directions = arrayOf(arrayOf(0,1),arrayOf(0,-1),arrayOf(1,0),arrayOf(-1,0))
    fun exist(board: Array<CharArray>, word: String): Boolean {

        val row = board.size
        val col = board[0].size
        val vis = Array(row) { BooleanArray(col) }
        for (i in 0 until row) {
            for (j in 0 until col) {
                if (board[i][j] == word[0] && isValid(board, word, i, j, 0, vis)) {
                    return true
                }
            }
        }
        return false
    }

    fun isValid(board: Array<CharArray>, word: String, bx: Int,by: Int, wi: Int, vis: Array<BooleanArray>): Boolean {

        if (bx < 0 || bx >= board.size || by < 0 || by >= board[0].size) return false
        if (vis[bx][by] == true) return false
        if (board[bx][by] != word[wi]) return false
        if (board[bx][by] == word[wi] && wi == word.length-1) {
            return true
        }

        vis[bx][by] = true
        for (dir in directions) {
            val x = dir[0]
            val y = dir[1]
            if (isValid(board, word, bx+x, by+y, wi+1, vis)) {
                return true
            }
        }
        vis[bx][by] = false
        return false
    }
}