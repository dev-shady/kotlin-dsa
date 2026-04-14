class Solution {
    fun threeSum(nums: IntArray): List<List<Int>> {

        val n = nums.size
        val triplets = ArrayList<List<Int>>()
        /*
        for future reference, checkout:
        If you know exactly how many items your cache will hold, set n to

        (the default load factor) to prevent any internal rehashing during its lifetime
        */
        val cache = HashMap<Int, MutableList<Int>>(n)
        val vis = HashMap<Int, Boolean>(n)
        repeat(n) {
            if (cache.containsKey(nums[it])) {
                cache.get(nums[it])?.add(it)
            } else {
                cache.put(nums[it], mutableListOf(it))
            }
        }

        var zeroes3 = false

        for (i in 0 until n) {
            val first = nums[i]
            if (vis.get(first) == true) {
                continue
            }
            val visNest = HashMap<Int, Boolean>(n)
            for (j in i + 1 until n) {
                val second = nums[j]
                if (visNest.getOrDefault(second, false)) {
                    continue
                }
                visNest[second] = true
                if (vis.get(second) == true) {
                    continue
                }
                val third = -1 * (nums[i] + nums[j])
                if (vis.get(third) == true) {
                    continue
                }
                val cacheValue = cache.get(third)
                if (cache.containsKey(third)) {
                    var found = false
                    for (k in 0 until cacheValue!!.size) {
                        if (cacheValue[k] <= i) {
                            continue
                        }
                        if (j != cacheValue[k]) {
                            found = true
                        }
                    }
                    for (k in 0 until cacheValue.size) {
                        if (cacheValue[k] <= i) {
                            continue
                        }
                        if (cacheValue[k] < j) {
                            found = false
                        }
                    }
                    if (!found) {
                        continue
                    }
                }

                if (cache.containsKey(third)) {
                    if (third == first || third == second) {
                        if ((third != 0 || !zeroes3) && cacheValue!!.size > 1) {
                            triplets.add(listOf(nums[i], nums[j], -1 * (nums[i] + nums[j])))
                            if (third == 0) {
                                zeroes3 = true
                            }
                        }
                    } else {
                        triplets.add(listOf(nums[i], nums[j], -1 * (nums[i] + nums[j])))
                    }
                }
            }
            vis.put(nums[i], true)
        }

        return triplets
    }
}

fun main() {
    println("triplet sum start")
    println(
            Solution()
                    .threeSum(intArrayOf(3, 0, 3, 2, -4, 0, -3, 2, 2, 0, -1, -5))
                    .joinToString(" ")
    )
}
