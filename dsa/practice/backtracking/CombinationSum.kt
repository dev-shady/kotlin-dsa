package practice.backtracking

import java.util.LinkedList

class CombinationSum {
    val result = ArrayList<ArrayList<Int>>()
    fun combinationSum(nums: IntArray, target: Int): List<List<Int>> {

        result.clear()
        val sortedNums = nums.sorted()
        process(sortedNums, LinkedList<Int>(), target, 0)
        return result
    }

    fun process(nums: List<Int>,subList: LinkedList<Int>, target: Int, idx: Int) {

        if (target == 0) {
            result.add(ArrayList(subList))
            return
        }

        for (i in idx until nums.size) {
            if (nums[i] > target) return
            subList.add(nums[i])
            process(nums, subList, target-nums[i], i)
            subList.removeLast()
        }
    }
}

fun main() {
    println(CombinationSum().combinationSum(intArrayOf(3,4,5), 16))
}