import java.util.LinkedList
import kotlin.math.max
import kotlin.math.min


/*
    Given a matrix grid[m][n] where m,n <=500, the matrix has 0 and 1. the variance of the matrix is the max distance
    to a 0 from a 1. the distance is max absolute distance possible from x axis or y axis, max(x1-x0, y1-y0).
    What is the minium variance can be achieved by turning one 0 to 1 at most.
 */
class MinVariance {

    fun minVarianceWithOneFlip(grid: Array<Array<Int>>): Int {

        val m = grid.size
        val n = grid[0].size

        // find all 1's and do multi source bfs to find max distance of each 0 from a 1
        val queue = LinkedList<Pair<Int, Int>>()
        val dist = Array(m, { IntArray(n, { Int.MAX_VALUE }) })
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 1) {
                    dist[i][j] = 0
                    queue.offer(Pair(i, j))
                }
            }
        }

        val dirs = arrayOf(Pair(-1,-1), Pair(-1,0), Pair(-1,1), Pair(0,-1),
            Pair(0,1), Pair(1,-1), Pair(1,0), Pair(1,1))
        var maxDist = 0

        while (!queue.isEmpty()) {
            val (x,y) = queue.poll()
            for (dir in dirs) {
                val newX = x + dir.first
                val newY = y + dir.second

                //if the cell hasn't been seen before
                if ( newX in 0 until m && newY in 0 until n && dist[newX][newY] == Int.MAX_VALUE) {
                    dist[newX][newY] = dist[x][y] + 1
                    queue.offer(Pair(newX, newY))
                    maxDist = max(maxDist, dist[newX][newY])
                }
            }
        }

        if (maxDist == 0) {
            return 0
        }

        // do a binary search from 0 to maxDist to find the max valid dist < maxDist
        // for a distance, find all the 0 cells Sk and their overlapping rectangle such that cells in that rectangle
        // have max dist < D for all the Sk cells

        var low = 1
        var high = maxDist
        var ans = maxDist

        while (low <= high) {
            var mid = low + (high - low) / 2
            val cells = findAllInvalidCellsForMaxDist(dist, mid)
            var r1 = 0
            var r2 = m-1
            var c1 = 0
            var c2 = n-1

            if (cells.isEmpty()) {
                ans = mid
                high = mid - 1
                continue
            }

            //find their overlapping rectangle such that cells in that rectangle
            // have max dist < D for all the Sk cells
            var isIntersectionPossible = true
            for (cell in cells) {
                val (x,y) = cell
                val xLeftBound = max(0, x-mid)
                val xRightBound = min(m-1, x+mid)
                val yLeftBound = max(0, y-mid)
                val yRightBound =  min(n-1, y+mid)

                r1 = max(r1, xLeftBound)
                r2 = min(r2, xRightBound)
                c1 = max(c1, yLeftBound)
                c2 = min(c2, yRightBound)

                // If window breaks, no point processing more cells for this mid
                if (r1>r2 || c1>c2) {
                    isIntersectionPossible = false
                    break
                }
            }

            if (isIntersectionPossible && areEmptyCellsPresent(grid, r1,r2,c1,c2)) {
                high = mid-1
                ans = mid
            } else {
                low = mid+1
            }
        }

        return ans

    }

    fun findAllInvalidCellsForMaxDist(dist: Array<IntArray>, distance: Int): List<Pair<Int, Int>> {
        val cells = ArrayList<Pair<Int, Int>>()
        val m = dist.size
        val n = dist[0].size
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (dist[i][j] > distance) {
                    cells.add(Pair(i, j))
                }
            }
        }
        return cells
    }

    fun areEmptyCellsPresent(grid: Array<Array<Int>>, r1:Int, r2: Int, c1:Int, c2:Int): Boolean {
        for (i in r1 .. r2) {
            for (j in c1 .. c2) {
                if (grid[i][j] == 0) {
                    return true
                }
            }
        }
        return false
    }
}