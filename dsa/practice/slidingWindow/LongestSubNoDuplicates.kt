package practice.slidingWindow

class LongestSubNoDuplicates {
    fun lengthOfLongestSubstring(s: String): Int {
        val cache = HashMap<Char, Int>()
        var start = 0
        var maxLen = 0
        s.forEachIndexed { index, ch ->
            if (cache.containsKey(ch) && cache[ch]!! >= start) {
                val idx = cache[ch]!!
                cache[ch] = index
                start = idx + 1
                maxLen = Math.max(maxLen, index - start + 1)

            } else {
                cache[ch] = index
                maxLen = Math.max(maxLen, index - start + 1)
            }
        }
        return maxLen
    }
}

fun main(args: Array<String>) {
    println(LongestSubNoDuplicates().lengthOfLongestSubstring("xxx"))
}