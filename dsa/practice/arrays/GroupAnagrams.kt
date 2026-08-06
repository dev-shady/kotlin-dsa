package practice.arrays

class GroupAnagrams {

    fun groupAnagrams(strs: Array<String>): List<List<String>> {

        val cache = HashMap<String, MutableList<String>>()
        val ALPHABET_SIZE = 26

        for (str in strs) {
            val charCount = IntArray(ALPHABET_SIZE)

            for (char in str) {
                charCount[char - 'a']++
            }

            val cacheKey = StringBuilder().apply {
                for (count in charCount) {
                    append('#')
                    append(count)
                }
            }.toString()

            if(!cache.containsKey(cacheKey)) {
                cache[cacheKey] = mutableListOf()
            }

            cache[cacheKey]?.add(str)
        }

        val result = ArrayList<List<String>>()
        cache.values.forEach { result.add(it) }
        return result
    }
}