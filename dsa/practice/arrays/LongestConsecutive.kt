package practice.arrays

class LongestConsecutive {
    fun longestConsecutive(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val cache = HashSet<Int>()
        for (num in nums) {
            cache.add(num)
        }

        val starters = ArrayList<Int>()
        for (num in nums) {
            if (!cache.contains(num-1)) {
                starters.add(num)
            }
        }

        var maxLength = 1
        var currentLength = 1
        for (num in starters) {
            currentLength = 1
            while (cache.contains(num + currentLength)) {
                currentLength++
            }
            maxLength = maxOf(maxLength, currentLength)
        }

        return maxLength

    }
}