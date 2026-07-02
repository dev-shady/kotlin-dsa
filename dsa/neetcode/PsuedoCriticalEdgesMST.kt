package neetcode

class PsuedoCriticalEdgesMST {

    data class Edge (val src: Int, val dest: Int, val weight: Int, val index: Int)

    fun findCriticalAndPseudoCriticalEdges(n: Int, edges: Array<IntArray>): List<List<Int>> {

        val result = ArrayList<ArrayList<Int>>()
        val edgesList = ArrayList<Edge>()
        for (i in 0 until edges.size) {
            val edge = edges[i]
            edgesList.add(Edge(edge[0], edge[1], edge[2], i))
        }

        edgesList.sortBy { e -> e.weight }
        val mstCost = kruskulMST(n, edgesList)
//        println("mstCost: $mstCost")
        val criticalEdges = ArrayList<Int>()
        val pseudoCriticalEdges = ArrayList<Int>()

        for (edge in edgesList) {
            val criticalMst = kruskulMST(n, edgesList, edge.index)
//            println("criticalMst: $criticalMst")
            if (criticalMst == -1 || criticalMst > mstCost) {
                criticalEdges.add(edge.index)
                continue
            }
            // check if it is psuedo
            val psuedoMst = kruskulMST(n, edgesList, -1, edge.index)
//            println("psuedoMst: $psuedoMst")
            if (psuedoMst == mstCost) {
                pseudoCriticalEdges.add(edge.index)
            }
        }

        result.add(criticalEdges)
        result.add(pseudoCriticalEdges)

        return result
    }

    private fun kruskulMST(n: Int, edges: ArrayList<Edge>, excludeEdge: Int = -1, includeEdge: Int = -1 ): Int {

        val dsu = DSU(n)
        var mstCost = 0
        var edgesCount = 0

        if (includeEdge != -1) {
            val edge = edges.first { it.index == includeEdge }
            dsu.union(edge.src, edge.dest)
            mstCost += edge.weight
            edgesCount++
        }

        for (edge in edges) {
            if (edge.index == excludeEdge || edge.index == includeEdge) {
                continue
            }
            if (dsu.union(edge.src, edge.dest)) {
                mstCost += edge.weight
                edgesCount++
                if (edgesCount == n-1) {
                    //MST found
                    return mstCost
                }
            }
        }

        return -1 // NO MST possible
    }

    class DSU(val n: Int) {
        val parent = IntArray(n) { it }
        val rank = IntArray(n) { 1 }

        fun find(i: Int): Int {
            if (parent[i] == i) {
                return i
            }
            parent[i] = find(parent[i])
            return parent[i]
        }

        fun union(i: Int, j: Int): Boolean {
            val rootI = find(i)
            val rootJ = find(j)
            if (rootI == rootJ) return false

            if (rank[rootI] > rank[j]) {
                parent[rootJ] = rootI
            } else if (rank[rootI] < rank[j]) {
                parent[rootI] = rootJ
            } else {
                parent[rootJ] = rootI
                rank[rootI]++
            }
            return true
        }
    }
}

fun main() {
    println(PsuedoCriticalEdgesMST().findCriticalAndPseudoCriticalEdges(4, arrayOf(intArrayOf(0,3,2), intArrayOf(0,2,5), intArrayOf(1,2,4))))
    println(PsuedoCriticalEdgesMST().findCriticalAndPseudoCriticalEdges(5, arrayOf(intArrayOf(0,3,2), intArrayOf(0,4,2), intArrayOf(1,3,2),
        intArrayOf(3,4,2),intArrayOf(2,3,1),intArrayOf(1,2,3),intArrayOf(0,1,1))))

}