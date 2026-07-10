package neetcode

import java.util.Stack

class LargestRectangleHistogram {

    fun largestRectangleArea(heights: IntArray): Int {

        val stack = Stack<Int>()
        val n = heights.size
        val left = IntArray(n)
        var right = IntArray(n)

        for (i in 0 until n) {
            if (stack.isEmpty()) {
                left[i] = i
                stack.push(i)
                continue
            }

            var top = stack.peek()
            val cur = heights[i]
            var curLeft = i

            while (heights[top] >= cur) {
                stack.pop()
                if (stack.isEmpty()) {
                    curLeft = 0
                    break
                }
                top = stack.peek()
                curLeft = top+1
            }

            left[i] = curLeft
            stack.push(i)
        }

        stack.clear()

        for (j in n-1 downTo 0) {
            if (stack.isEmpty()) {
                right[j] = j
                stack.push(j)
                continue
            }

            var top = stack.peek()
            val cur = heights[j]
            var curRight = j

            while (heights[top] >= cur) {
                stack.pop()
                if (stack.isEmpty()) {
                    curRight = n-1
                    break
                }
                top = stack.peek()
                curRight = top-1
            }

            right[j] = curRight
            stack.push(j)
        }

        var maxArea = 0
//        println(left.contentToString())
//        println(right.contentToString())
        for (i in 0 until n) {
            maxArea = Math.max(maxArea, (right[i] -left[i] +1)* heights[i])
        }

        return maxArea
    }

    fun largestRectangleAreaOptimal(heights: IntArray): Int {
        val stack = Stack<Int>()
        val n = heights.size
        val left = IntArray(n)
        var right = IntArray(n)
        var curLeft = -1
        var  curRight = -1
        for (i in 0 until n) {
            if (stack.isEmpty()) {
                left[i] = i
                stack.push(i)
                continue
            }

            var top = stack.peek()
            val cur = heights[i]
            curLeft = i

            while (heights[top] >= cur) {
                right[top] = i-1
                stack.pop()
                if (stack.isEmpty()) {
                    curLeft = 0
                    break
                }
                top = stack.peek()
                curLeft = top+1
            }

            left[i] = curLeft
            stack.push(i)
        }

        while (stack.isNotEmpty()) {
            var cur = stack.pop()
            right[cur] = n-1
        }

        for ( i in n-1 downTo 1 ) {
            if (heights[i] == heights[i-1]) {
                right[i-1] = right[i]
            }
        }

        var maxArea = 0
        for (i in 0 until n) {
            maxArea = Math.max(maxArea, (right[i] -left[i] +1)* heights[i])
        }

        return maxArea
    }
}

fun main() {
    println(LargestRectangleHistogram().largestRectangleAreaOptimal(intArrayOf(7,1,7,2,2,4)))
    println(LargestRectangleHistogram().largestRectangleAreaOptimal(intArrayOf(1,3,7)))
    println(LargestRectangleHistogram().largestRectangleAreaOptimal(intArrayOf(3,7,4,4,4,4,4,2,4)))


}