package leetcode

class NoOfIslands2 {

    fun isValid(x: Int, y: Int,n: Int, m: Int): Boolean {
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false
        }
        return true
    }
    fun numOfIslands(n: Int, m: Int, inputs: Array<IntArray>): IntArray {

        var islandsCount = 0
        val islands = BooleanArray(n*m)
        val result = IntArray(inputs.size)
        val xChanges = intArrayOf(-1,1,0,0)
        val yChanges = intArrayOf(0,0,-1,1)
        val dsu = DSU(n*m)

        for (i in inputs.indices) {
            val inp = inputs[i]
            val x = inp[0]
            val y = inp[1]
            if (islands[x*m+y]) {
                result[i] = islandsCount
                continue
            }
            islands[x*m+y] = true
            islandsCount++

            for  (j in 0 until 4) {
                var a = x + xChanges[j]
                var b = y + yChanges[j]
                if(!isValid(a, b, n, m)) {
                    continue
                }
                if (islands[a*m+b]) {
                    // merge a,b with x,y
                    if (dsu.union(a*m+b, x*m+y)) {
                        islandsCount--
                    }
                }
            }

            result[i] = islandsCount
        }
        return result
    }

    class DSU(n: Int) {
        val parent = IntArray(n) { it }
        val rank = IntArray(n) { 1 }

        fun find(n: Int): Int {
            if (parent[n] == n) {
                return n
            }
            parent[n] = find(parent[n])
            return parent[n]
        }

        fun union(i: Int, j: Int): Boolean {
            val parentI = find(i)
            val parentJ = find(j)

            if (parentI == parentJ) {
                return false
            }

            if (rank[parentI] > rank[parentJ]) {
                parent[parentJ] = parentI
            } else if (rank[parentI] < rank[parentJ]) {
                parent[parentI] = parentJ
            } else {
                parent[parentJ] = parentI
                rank[parentI]++
            }
            return true
        }
    }
}

fun main() {
    println(NoOfIslands2().numOfIslands(4, 5, arrayOf(intArrayOf(1,1),intArrayOf(0,1),intArrayOf(3,3),intArrayOf(3,4))).contentToString())
    println(NoOfIslands2().numOfIslands(4, 5, arrayOf(
        intArrayOf(0,0),intArrayOf(0,0),intArrayOf(1,1),intArrayOf(1,0),
        intArrayOf(0,1),intArrayOf(0,3),intArrayOf(1,3),intArrayOf(0,4),
        intArrayOf(3,2),intArrayOf(2,2),intArrayOf(1,2),intArrayOf(0,2))).contentToString())

}