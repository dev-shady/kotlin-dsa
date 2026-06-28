package neetcode

import java.util.PriorityQueue
import kotlin.math.max

class SwimRiseWater {
    data class GridEntry(var row: Int, var col: Int, var value: Int)

    fun swimInWater(grid: Array<IntArray>): Int {

        var maxTime = 0
        val n = grid.size

        val visited = Array(n) { BooleanArray(n)}
        val queue  = PriorityQueue<GridEntry>(n*n) {a,b  -> a.value - b.value }

        queue.add(GridEntry(0, 0, grid[0][0]))
        visited[0][0] = true

        while (queue.isNotEmpty()) {
            val entry = queue.remove()
            val x = entry.row
            val y = entry.col
            val time = entry.value
            maxTime = max(maxTime, time)

            if (x==n-1 && y==n-1) {
                return maxTime
            }

            if (y+1 < n && visited[x][y+1] == false) {
                queue.add(GridEntry(x, y+1, max(time,grid[x][y+1])))
                visited[x][y+1] = true
            }

            if (y-1 >= 0 && visited[x][y-1] == false) {
                queue.add(GridEntry(x, y-1, max(time,grid[x][y-1])))
                visited[x][y-1] = true
            }

            if (x+1 < n && visited[x+1][y] == false) {
                queue.add(GridEntry(x+1, y, max(time,grid[x+1][y])))
                visited[x+1][y] = true
            }

            if (x-1 >= 0 && visited[x-1][y] == false) {
                queue.add(GridEntry(x-1, y, max(time,grid[x-1][y])))
                visited[x-1][y] = true
            }
        }

        return maxTime
    }
}

fun main(args: Array<String>) {
    println(SwimRiseWater().swimInWater(arrayOf(
        intArrayOf(0,1,2,10),
        intArrayOf(9,14,4,13),
        intArrayOf(12,3,8,15),
        intArrayOf(11,5,7,6),
    )))
    println(SwimRiseWater().swimInWater(arrayOf(
        intArrayOf(0,1),
        intArrayOf(2,3),
    )))
}