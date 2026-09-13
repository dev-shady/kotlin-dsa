package leetcode.set75

import kotlin.math.max

class LongestSubstringWithoutDuplicate {

    fun lengthOfLongestSubstring(s: String): Int {
        var maxLength = 1
        val len = s.length

        if (len == 0) return 0

        var start = 0
        var end = 0
        val cache = mutableMapOf<Char, Int>() // (char, lastIndex)
        cache.put(s[0], 0)

        for (i in 1 until len) {
            if (!cache.contains(s[i])) {
                cache[s[i]] = i
                maxLength = max(maxLength, i-start+1)
                continue
            }

            val lastPos = cache[s[i]]!!
            if (start > lastPos) {
                maxLength = max(maxLength, i-start+1)
                cache[s[i]] = i
                continue
            } else {
                start = lastPos+1
                cache[s[i]] = i
            }
        }

        return maxLength
    }

    fun lengthOfLongestSubstringOptimized(s: String): Int {
    var maxLength = 0
    var start = 0
    val cache = mutableMapOf<Char, Int>()

    for (i in 0 until s.length) {
        // If the character is in our current window, jump start past its last position
        if (cache.containsKey(s[i]) && cache[s[i]]!! >= start) {
            start = cache[s[i]]!! + 1
        }
        
        // Always update the most recent position of the character
        cache[s[i]] = i
        maxLength = maxOf(maxLength, i - start + 1)
    }
    return maxLength
}


}

fun main() {
    println(LongestSubstringWithoutDuplicate().lengthOfLongestSubstring("pwwkew"))
}
