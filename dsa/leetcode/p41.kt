package dsa.leetcode.p41
fun main() {
    println("First Missing Postive")
    println(firstMissingPositive(intArrayOf(10, 5, 1, 4, 2, 3, 60, 8, 9)))
    println(firstMissingPositive(intArrayOf(1, 2, 0)))
    println(firstMissingPositive(intArrayOf(3, 4, -1, 1)))
    println(firstMissingPositive(intArrayOf(7, 8, 9, 11, 12)))
    println(firstMissingPositive(intArrayOf(2, 2)))
    println(firstMissingPositive(intArrayOf(100000, 3, 4000, 2, 15, 1, 99999)))
    println(firstMissingPositive(intArrayOf(0, 2, 2, 4, 0, 1, 0, 1, 3)))
}

// not covering all cases, messy first approch
fun firstMissingPositive2(nums: IntArray): Int {
    val n = nums.size
    var low = 1
    var high = Int.MAX_VALUE
    var highParent = -1
    var maxVisited = -1

    var idx = 0
    while (idx < n) {

        // println("${low} ${high} idx= ${idx} P= ${highParent} M= ${maxVisited}")
        // println("hh")

        if (nums[idx] < low) {
            idx++
            continue
        }

        if (low == high) {
            // println("low == high ${maxVisited} ${highParent}")
            // all numbers till low have been seen
            // start again with new low
            // idx = 0
            if (nums[idx] == low) {
                low = Math.max(maxVisited, highParent) + 1
                high = Int.MAX_VALUE
                idx++
            } else {
                // high = Math.max(maxVisited, highParent) + 1
                high = Int.MAX_VALUE
            }
            maxVisited = highParent
            // idx++
            highParent = -1
            continue
        }

        if (nums[idx] == low) {
            low = Math.max(maxVisited + 1, low + 1)
            idx++
            continue
        }

        if (maxVisited != -1 && maxVisited + 1 == nums[idx]) {
            maxVisited++
            idx++
            continue
        }

        if (highParent != -1 && highParent + 1 == nums[idx]) {
            highParent++
            idx++
            continue
        }

        if ((maxVisited == -1 || nums[idx] > maxVisited) && nums[idx] <= high) {

            if (nums[idx] != high) {
                highParent = nums[idx]
            }
            high = nums[idx] - 1
        }
        // high = Math.min(high, nums[idx] - 1)

        idx++
    }

    // if (low + 1 == high) {
    //     return high + 1
    // } else {
    //     return low
    // }
    // println("${low}-${high}")
    repeat(n) { i ->
        if (nums[i] == low) {
            low++
        }
    }
    return low
}

fun firstMissingPositive(nums: IntArray): Int {
    val n = nums.size

    repeat(n) { i ->
        while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
            var tmp = nums[nums[i] - 1]
            nums[nums[i] - 1] = nums[i]
            nums[i] = tmp
        }
    }

    // println(nums.joinToString(" "))

    repeat(n) { i ->
        if (nums[i] != i + 1) {
            return if (i == 0) 1 else nums[i - 1] + 1
        }
    }

    return n + 1
}
