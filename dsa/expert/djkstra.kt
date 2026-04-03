import java.util.PriorityQueue

fun dijkstra(adj: List<List<IntArray>>, src: Int): List<Int> {
    val v = adj.size

    // Min-heap storing [distance, node]
    val pq = PriorityQueue<IntArray>(compareBy { it[0] })

    val dist = IntArray(v) { Int.MAX_VALUE }

    dist[src] = 0
    pq.offer(intArrayOf(0, src))

    while (pq.isNotEmpty()) {
        val (d, u) = pq.poll() ?: continue

        if (d > dist[u]) continue

        for (edge in adj[u]) {
            val destination = edge[0]
            val weight = edge[1]

            if (dist[u] != Int.MAX_VALUE && dist[u] + weight < dist[destination]) {
                dist[destination] = dist[u] + weight
                pq.offer(intArrayOf(dist[destination], destination))
            }
        }
    }

    // toList() converts the IntArray to an immutable List<Int>
    return dist.toList()
}

fun main() {
    // Creates a list containing V empty mutable lists
    val V = 5
    val adj: List<MutableList<IntArray>> = List(V) { mutableListOf() }

    // To add an edge from 'u' to 'v' with weight 'w'
    // adj[u].add(intArrayOf(v, w))
}
