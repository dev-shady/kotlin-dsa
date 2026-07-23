package neetcode

class NQueensBitmask {

        var N = -1
        var count = 0

        fun totalNQueens(n: Int): Int {

            count = 0
            var targetBitmask = (1 shl n) - 1
            N = n
            countNQueens(0, 0, 0, targetBitmask)
            return count
        }

        private fun countNQueens(cols: Int, posDiagonal: Int, negDiagonal: Int, target: Int) {
            if (cols == target) {
                count++
                return
            }

            var safeSpots = (cols or posDiagonal or negDiagonal).inv() and target

            while (safeSpots > 0) {
                val lowestSafeBit = safeSpots and -safeSpots
                countNQueens(cols or lowestSafeBit, ( posDiagonal or lowestSafeBit) ushr 1, (negDiagonal or lowestSafeBit) shl 1, target)
                safeSpots = safeSpots xor lowestSafeBit
            }
        }
}

fun main() {
    println(NQueensBitmask().totalNQueens(1))
    println(NQueensBitmask().totalNQueens(4))

}