fun main() {
    println("Topological Sort")
    val n = 6
    val adj = ArrayList<ArrayList<Int>>()

    repeat(n) { adj.add(ArrayList()) }

    addEdge(adj, 0, 1)
    addEdge(adj, 1, 2)
    addEdge(adj, 2, 3)
    addEdge(adj, 4, 5)
    addEdge(adj, 5, 1)
    addEdge(adj, 5, 2)

    topologicalSort(adj)
}

fun addEdge(adj: ArrayList<ArrayList<Int>>, u: Int, v: Int) {
    adj[u].add(v)
}

fun topologicalSort(adjList: ArrayList<ArrayList<Int>>) {
    val n = adjList.size
    var inDeg = IntArray(n)
    val queue = ArrayDeque<Int>()
    val result = ArrayList<Int>()

    // build inDegree
    for (i in 0 until n) {
        for (j in adjList[i]) {
            inDeg[j]++
        }
    }

    for (i in 0 until n) {
        if (inDeg[i] == 0) {
            queue.addLast(i)
        }
    }

    while (queue.isNotEmpty()) {
        val u = queue.removeFirst()
        result.add(u)

        for (v in adjList[u]) {
            inDeg[v]--
            if (inDeg[v] == 0) {
                queue.addLast(v)
            }
        }
    }

    println(result.joinToString(" -> "))
}
