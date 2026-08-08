package practice.slidingWindow

import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle

class LongestRepeatingChar {
    fun characterReplacement(s: String, k: Int): Int {
        val charCount = IntArray(26)
        var left = 0

        var j = left
        var maxFreq = 0
        var maxLen = 0
        while ( j < s.length) {
            charCount[s[j] - 'A']++
            val count = charCount[s[j] - 'A']
            maxFreq = Math.max(maxFreq, count)

            if ((j-left+1) - maxFreq > k) {
                // shorten the window
                charCount[s[left] - 'A']--
                left++
            }

            maxLen = Math.max(maxLen, (j-left+1))
            j++
        }
        return maxLen
    }
}