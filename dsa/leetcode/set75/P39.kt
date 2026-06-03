package leetcode.set75

class CombinationSum {

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {

        val result = mutableListOf<List<Int>>()
        candidates.sortedBy { it }
        val current = mutableListOf<Int>()

        findCombinations(0, candidates, target, result, current)

        return result
    }

    private fun findCombinations(start: Int, candidates: IntArray, target: Int, result: MutableList<List<Int>>, current: MutableList<Int>) {
//        println("findCombinations ${start} -- ${target} -- ${current}")
        if (start >= candidates.size) {
            return
        }
        if (target == 0) {
            result.add(current.toList())
            return
        }

        for (i in start until candidates.size) {
            val num = candidates[i]
            if (num <= target) {
                current.add(num)
                findCombinations(i,candidates, target-num, result, current)
                current.removeLast()
            }
        }
    }
}

fun main() {
    println(CombinationSum().combinationSum(intArrayOf(2,3,5), 8))
}