fun main() {
    println("Max Flow Ford Fulkerson")
    val graph =
            arrayOf(
                    intArrayOf(0, 16, 13, 0, 0, 0),
                    intArrayOf(0, 0, 10, 12, 0, 0),
                    intArrayOf(0, 4, 0, 0, 14, 0),
                    intArrayOf(0, 0, 9, 0, 0, 20),
                    intArrayOf(0, 0, 0, 7, 0, 4),
                    intArrayOf(0, 0, 0, 0, 0, 0)
            )

    println("the max flow is ${maxFlow(graph, 0, 5, 6)}")
}

fun bfs(graph: Array<IntArray>, s: Int, t: Int, parent: IntArray, n: Int): Boolean {

    val queue = ArrayDeque<Int>()
    val vis = BooleanArray(n)
    queue.add(s)
    vis[s] = true
    parent[s] = -1

    while (queue.isNotEmpty()) {
        val u = queue.removeFirst()

        for (v in 0 until n) {
            if (graph[u][v] > 0 && vis[v] == false) {
                if (v == t) {
                    parent[v] = u
                    return true
                }
                queue.add(v)
                vis[v] = true
                parent[v] = u
            }
        }
    }
    return false
}

fun maxFlow(
        graph: Array<IntArray>,
        s: Int,
        t: Int,
        n: Int,
): Int {
    var max_flow = 0
    // residual graph
    var resGraph = Array(n) { IntArray(n) }
    var parent = IntArray(n) { -1 }

    for (i in 0 until n) {
        for (j in 0 until n) {
            resGraph[i][j] = graph[i][j]
        }
    }

    while (bfs(resGraph, s, t, parent, n)) {
        var v = t
        var cur_flow = Int.MAX_VALUE
        while (v != s) {
            var u = parent[v]
            cur_flow = Math.min(cur_flow, resGraph[u][v])
            v = u
        }

        v = t
        while (v != s) {
            var u = parent[v]
            resGraph[u][v] -= cur_flow
            resGraph[v][u] += cur_flow
            v = u
        }

        max_flow += cur_flow
    }

    return max_flow
}
