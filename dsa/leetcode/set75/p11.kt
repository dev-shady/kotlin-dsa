package dsa.leetcode.set75.p11

fun main() {
    println("Container with most water")
    println(Solution().maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)))
    println(Solution().maxArea(intArrayOf(8, 7, 2, 1)))
    println(Solution().maxArea(intArrayOf(2, 3, 4, 5, 18, 17, 6)))
}

class Solution {

    /*
    tried to find max height on left side of each pillar and
    max height on right side of each piller to find max water.
    But this doesn't provide the farthest left >= height[cur] or
    farthest right >= height[cur] so it doesn't always give best solution.
    */
    fun maxAreaIncorrect(height: IntArray): Int {
        val n = height.size

        if (n == 1) {
            return 0
        }

        if (n == 2) {
            return Math.min(height[0], height[1])
        }

        val leftMax = IntArray(n)
        val rightMax = IntArray(n)

        var curMaxIndex = 0
        leftMax[1] = curMaxIndex
        (2 until n).forEach { i ->
            if (height[curMaxIndex] < height[i - 1]) {
                curMaxIndex = i - 1
            }
            leftMax[i] = curMaxIndex
        }

        curMaxIndex = n - 1
        rightMax[n - 2] = curMaxIndex
        (n - 3 downTo 0).forEach { i ->
            if (height[curMaxIndex] < height[i + 1]) {
                curMaxIndex = i + 1
            }
            rightMax[i] = curMaxIndex
        }

        leftMax[0] = -1
        rightMax[n - 1] = -1

        // println(leftMax.joinToString(" "))
        // println(rightMax.joinToString(" "))

        var maxWater = 0
        repeat(n) { i ->
            if (leftMax[i] != -1 && rightMax[i] != -1) {
                val left = height[leftMax[i]]
                val right = height[rightMax[i]]
                val water = Math.min(left, right) * (rightMax[i] - leftMax[i])
                maxWater = Math.max(maxWater, water)
            }
        }

        repeat(n - 1) { i -> maxWater = Math.max(maxWater, Math.min(height[i], height[i + 1])) }

        return maxWater
    }

    fun maxArea(height: IntArray): Int {
        val n = height.size

        var curLeft = 0
        var curRight = n - 1
        var maxWater = 0

        while (curLeft < curRight) {

            if (height[curLeft] < height[curRight]) {
                val water = height[curLeft] * (curRight - curLeft)
                maxWater = Math.max(maxWater, water)
                curLeft++
            } else if (height[curLeft] > height[curRight]) {
                val water = height[curRight] * (curRight - curLeft)
                maxWater = Math.max(maxWater, water)
                curRight--
            } else {
                val water = height[curLeft] * (curRight - curLeft)
                maxWater = Math.max(maxWater, water)
                curLeft++
                curRight--
            }
        }

        return maxWater
    }
}
