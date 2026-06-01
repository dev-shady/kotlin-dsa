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

}

fun main() {
    println(LongestSubstringWithoutDuplicate().lengthOfLongestSubstring("pwwkew"))
}