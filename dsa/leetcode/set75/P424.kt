package leetcode.set75

class LongestRepeatingSubstring {

    fun characterReplacement(s: String, k: Int): Int {
        val len = s.length

        if (len == 0) return 0
        if (len == 1) return 1

        if (len-1 <= k) return k

        val charCount = IntArray(26) {0}
        var charMaxCount = 0
        var curLeft = 0
        var curLen = 0

        for (i in 0 until len) {

            val newCount = charCount[s[i] - 'A'] + 1
            val len = i-curLeft + 1
            charCount[s[i] - 'A'] = newCount

            if (newCount > charMaxCount) {
                charMaxCount = newCount
                curLen = len
            } else if (len - charMaxCount <= k) {
                curLen = len
                continue
            } else {
                charCount[s[curLeft]-'A'] =  charCount[s[curLeft]-'A'] - 1
                curLeft++
            }
        }

        return curLen

    }
}

fun main() {
    println(LongestRepeatingSubstring().characterReplacement("", 0))
    println(LongestRepeatingSubstring().characterReplacement("AABABBA", 0))

}