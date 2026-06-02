package leetcode.set75

/*
133. Clone Graph
for this problem, don't give the full solution. just guide me like a interviewer.
I am thinking about
1. starting from given node,
2. create its clone (new node with same value, adjList we will start filling later)
3. put this clone in map<Int, Node>
4. loop over its adjacency list.
5. if id is present in cache, add the reference to new node (clone) adj list.
6. if id not present in cache, means clonse doesn't exist. Do a DFS on this, following above algo (creating clone, adding to cache and looping over adjList)
7. whenever a DFS of an item of the loop completes, add the clone from cache to adjList and move to next iteration of loop.
 */

class CloneGraph {

    class Node(var `val`: Int) {
        var neighbors: ArrayList<Node?> = ArrayList<Node?>()
    }

    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null

        val cache = mutableMapOf<Int, Node>()
        return deepClone(node, cache)

    }

    fun deepClone(node: Node, cache: MutableMap<Int, Node>): Node {

        if (cache.containsKey(node.`val`)) {
            return cache[node.`val`]!!
        }

        val newNode = Node(node.`val`)
        cache.put(node.`val`, newNode)

        for (neighbor in node.neighbors) {
            neighbor?.let {
                newNode.neighbors.add(deepClone(neighbor, cache))
            }
        }

        return newNode
    }
}