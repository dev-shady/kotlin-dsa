package leetcode.set75

/*
problem 139. Word Break
Algo:
1. create a trie from all the dictionary words
2. start iterating over the input string and traverse the corresponding path in trie.
3. if at any point, there is no fwd path, return false
4. if path exists, keep traversing trie until we find the word ending node.
5. we can consider this first partition and start new traversal in trie from root to check if the remaining input string can be broken down into a set of words in similar fashion.
6. at any point if fwd path doesn't exist, just return false.
7. if a recursive calls returns false then we ignore the current partition (as it doesn't lead to complete breakup) and find another partition.
8. return true if whole input string can be broken in to a set of words otherwise false
 */
/*
forgot, add Memoization for avoiding repetitive calls
 */
/*
Alternatively, since the dictionary size in this LeetCode problem is usually quite small, many candidates skip the Trie entirely.
They just use a simple HashSet<String> for the dictionary and a BooleanArray for dynamic programming.
 */

class WordBreak {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        return canBreak(s, 0, mutableMapOf<Int, Boolean>(), wordDict)
    }

    fun canBreak(s: String, start: Int, cache: MutableMap<Int, Boolean>, wordDict: List<String>): Boolean {

        if (start >= s.length) {
            return true
        }

        if (cache.containsKey(start)) {
            return cache[start]!!
        }
        val len = s.length
        for (i in start+1..len) {
            val word = s.substring(start, i)
            if (wordDict.contains(word)) {
                val isBreakable = canBreak(s, i, cache, wordDict)
                if (isBreakable) {
                    cache.put(start, isBreakable)
                    return true
                }
            }
        }
        cache.put(start, false)
        return false
    }
}

fun main() {
    println(WordBreak().wordBreak("leetcode", listOf("leet","code")))
    println(WordBreak().wordBreak("applepenapple", listOf("apple","pen")))
    println(WordBreak().wordBreak("catsandog", listOf("cats","dog","sand","and","cat")))

}