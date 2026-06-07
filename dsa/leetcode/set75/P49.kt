package leetcode.set75

class GroupAnagrams {

    /*
        1. create a pair for each word . eg (eat, eat), (tea, tea)...
        2. sort first item of each pair
        3. sort whole array based on first item of pair
        4. from first to last pair in sorted array, anagrams would be concecutive. just do a single loop and add them in groups.
        5. Another optimized solution is to take hash of the string and put them in a hashmap
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {

        return emptyList()
    }
}