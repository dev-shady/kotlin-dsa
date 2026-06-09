package leetcode.set75

class DecodeWays {
    /*
        This is also a DP problem.
        1. put '1','2'...'26' in a hash map.
        2. for the given string , start with 0 index .
        3. consider all the possibilities to decode the start. for '11234', there is '1' & '11'.
        4. so total possible decoding = decodings(1) + decodings(2) where number represents the start of new substring.
        5. decodings(i) = if s[i] is valid, decodings(i+1). if s[i][i+1] is a valid decoding, decodings(i+2). their sum represents the total possible decodings.
        6. Hashmap can be used to check if a substring is a valid decoding or not.
        7. save this decoding in a cache[i] to not repeat calculations for same subproblem.
     */

    fun isValid(s: String): Boolean {
        if (s.length == 0) return false
        if (s[0] == '0') {
            return false
        }
        if (s.length == 1) {
            return true
        }
        if (s.length == 2) {
            if (s[0] == '1') {
               return true
            } else if (s[0] == '2') {
                val value = s[1] - '0'
                if (value <= 6) {
                    return true
                } else {
                    return false
                }
            }
        }
        return false
    }

    fun numDecodings(s: String): Int {
        val len = s.length
        if (len == 0) return 0
        if (s[0] == '0') return 0
        val cache = mutableMapOf<Int, Int>()
        return decodeWays(s,0, cache)
    }

    fun decodeWays(s: String, start: Int, cache: MutableMap<Int, Int>): Int {
        val len = s.length
        if (start >= len) return 1

        if (cache.contains(start)) {
            return cache[start]!!
        }

        val first = s.substring(start, start+1)
        val second = if (len - start >= 2) s.substring(start, start + 2) else ""
        var ways = 0
        if (isValid(first)) {
            ways += decodeWays(s,start+1, cache)
        }

        if (isValid(second)) {
            ways += decodeWays(s,start+2, cache)
        }
        cache[start] = ways
        return ways
    }
}

fun main(args: Array<String>) {
    println(DecodeWays().numDecodings("12"))
    println(DecodeWays().numDecodings("226"))
    println(DecodeWays().numDecodings("06"))
}
