package neetcode

import java.util.LinkedList

class BuildMatrix {
    fun buildMatrix(k: Int, rowConditions: Array<IntArray>, colConditions: Array<IntArray>): Array<IntArray> {

        val finalMatrix = Array(k) { IntArray(k) { 0 } }

        val rowSize = rowConditions.size
        val colSize = colConditions.size

        var adjacencyList = Array(k+1) { ArrayList<Int>() }

        for (condition in rowConditions) {
            adjacencyList[condition[0]].add(condition[1])
        }

        val rowOrder = topologicalSort(k, adjacencyList)
        println(rowOrder.contentToString())
        if (rowOrder == null) {
            return emptyArray()
        }

        adjacencyList = Array(k+1) { ArrayList<Int>() }
        for (condition in colConditions) {
            adjacencyList[condition[0]].add(condition[1])
        }
        val colOrder = topologicalSort(k, adjacencyList)
        println(colOrder.contentToString())
        if (colOrder == null) {
            return emptyArray()
        }

        val rowPosition = IntArray(k+1)
        val colPosition = IntArray(k+1)

        for (i in 0 until k) {
            val rowItem = rowOrder[i]
            val colItem = colOrder[i]

            rowPosition[rowItem] = i
            colPosition[colItem] = i
        }

        for (i in 1..k) {
            val x = rowPosition[i]
            val y = colPosition[i]

            finalMatrix[x][y] = i
        }

        return finalMatrix

    }

    fun topologicalSort(k: Int, adjacencyList: Array<ArrayList<Int>>): IntArray? {

        val inDegree = IntArray(k+1)
        for (neighbours in adjacencyList) {
            for (neighbour in neighbours) {
                inDegree[neighbour]++
            }
        }

        val queue = LinkedList<Int>()
        for (i in 1..k) {
            if (inDegree[i] == 0) {
                queue.add(i)
            }
        }

        val orderedItems = IntArray(k)
        var index = 0

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            orderedItems[index++] = cur

            val neighbours = adjacencyList[cur]
            if (neighbours.isNotEmpty()) {
                for (neigbour in neighbours) {
                    inDegree[neigbour]--
                    if (inDegree[neigbour] == 0) {
                        queue.add(neigbour)
                    }
                }
            }
        }

        if (index == k) return orderedItems
        return null
    }
}

fun main(args: Array<String>) {
    println(BuildMatrix().buildMatrix(3,
        arrayOf(intArrayOf(2, 1), intArrayOf(1, 3)),
        arrayOf(intArrayOf(3, 1), intArrayOf(2, 3))).contentDeepToString())

    println(BuildMatrix().buildMatrix(3,
        arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 1), intArrayOf(2, 3)),
        arrayOf(intArrayOf(2, 1))).contentDeepToString())
}