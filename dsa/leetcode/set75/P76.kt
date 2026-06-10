package leetcode.set75

import kotlin.math.min

class MinWindowSubstring {
    /**
     * sliding window two pointer approach
     */
    fun minWindow(s: String, t: String): String {

        val tLen = t.length
        val sLen = s.length

        if (tLen > sLen) return ""

        var formed = 0

        var curLeft = 0
        var curRight = 0
        val charCount = HashMap<Char, Int>(30)

        val tCache = HashMap<Char, Int>()
        for (i in 0 until tLen) {
            tCache[t[i]] = tCache.getOrDefault(t[i], 0) + 1
        }
        var minWindow = Int.MAX_VALUE
        var windowLeft = -1
        var windowRight = -1

        while (curLeft < sLen && !tCache.containsKey(s[curLeft])) {
            curLeft++
        }

        if (curLeft >= sLen || !tCache.containsKey(s[curLeft])) {
            return ""
        } else {
            curRight = curLeft
        }

        while (curLeft < sLen && curRight < sLen) {

            if (tCache.containsKey(s[curRight])) {
                if (tCache[s[curRight]]!! > charCount.getOrDefault(s[curRight], 0)) {
                    formed++
                    if (formed == tLen) {
                        val curWindow = curRight - curLeft + 1
                        if (minWindow > curWindow) {
                            minWindow = curWindow
                            windowLeft = curLeft
                            windowRight = curRight
                        }

                        if (charCount.containsKey(s[curLeft])) {
                            charCount[s[curLeft]] = charCount[s[curLeft]]!! - 1
                        }
                        if (tCache[s[curLeft]]!! > charCount.getOrDefault(s[curLeft], 0)) {
                                formed--
                        }
                        curLeft++
                        while (formed == tLen) {
                            if (tCache.containsKey(s[curLeft])) {
                                val curWindow = curRight - curLeft + 1
                                if (minWindow > curWindow) {
                                    minWindow = curWindow
                                    windowLeft = curLeft
                                    windowRight = curRight
                                }
                                charCount[s[curLeft]] = charCount[s[curLeft]]!! - 1
                                if (tCache[s[curLeft]]!! > charCount.getOrDefault(s[curLeft], 0)) {
                                    formed--
                                }
                                curLeft++
                            } else {
                                curLeft++
                            }
                        }
                    }
                }
                charCount[s[curRight]] = charCount.getOrDefault(s[curRight], 0) + 1
            }

            while (curLeft < sLen && !tCache.containsKey(s[curLeft])) {
                curLeft++
            }

            curRight++
        }

        if (minWindow != Int.MAX_VALUE) {
            return s.substring(windowLeft, windowRight+1)
        } else {
            return ""
        }
    }
}

fun main() {
    println(MinWindowSubstring().minWindow("ADOBECODEBANC", "ABC"))
    println(MinWindowSubstring().minWindow("bba", "ab"))
    println(MinWindowSubstring().minWindow("a", "aa"))
    println(MinWindowSubstring().minWindow("bba", "ab"))
    println(MinWindowSubstring().minWindow("a", "b"))


}