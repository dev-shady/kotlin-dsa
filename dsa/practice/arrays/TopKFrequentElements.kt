package practice.arrays

import java.util.PriorityQueue

class TopKFrequentElements {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {

        val queue = PriorityQueue<Pair<Int, Int>>(k, compareBy { it.second })
        val countCache = HashMap<Int, Int>()
        for (i in nums.indices) {
            countCache[nums[i]] = countCache.getOrDefault(nums[i], 0) + 1
        }

      for ((num, count) in countCache) {
          queue.add(Pair(num, count))
          if (queue.size > k) {
              queue.poll()
          }
      }

        val res = IntArray(k)
        for (i in 0 until k) {
            res[i] = queue.poll().first
        }
        return res
    }
}