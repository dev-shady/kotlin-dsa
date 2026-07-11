package neetcode

class TrappedRainWater {
    fun trap(height: IntArray): Int {

        val n = height.size
        var left = 0
        var right = n - 1
        var leftMax = 0
        var rightMax = 0
        var water = 0

        while (left < right) {

            if (height[left] < height[right]) {
                // leftMax is the bottleneck for current left bar as leftMax < rightMax
                if (height[left] > leftMax) {
                    leftMax = height[left]
                } else {
                    water += (leftMax - height[left])
                }
                left++
            } else {
                // rightMax is the bottleneck for current right bar as leftMax >= rightMax
                if (height[right] > rightMax) {
                    rightMax = height[right]
                } else {
                    water += (rightMax - height[right])
                }
                right--
            }

        }
        return water
    }
}