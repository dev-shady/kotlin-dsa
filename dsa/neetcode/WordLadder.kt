package neetcode

import java.util.LinkedList

class WordLadder {
    fun ladderLength(beginWord: String, endWord: String, wordList: MutableList<String>): Int {
        var ladderLength = 0


        //build the graph first
        val dictionary = HashMap<String, MutableList<String>>()

        for (word in wordList) {
            for (i in 0 until word.length) {
                val newWord = word.substring(0, i) + "*" + word.substring(i + 1)
                dictionary.getOrPut(newWord) {mutableListOf(word)}.add(word)
            }
        }

        // bfs starting with beginWord
        val queue = LinkedList<String>()
        val visited = HashSet<String>()
        queue.add(beginWord)
        visited.add(beginWord)

        while (queue.isNotEmpty()) {
            val size = queue.size
            ladderLength++
            for (i in 0 until size) {
                val word = queue.removeFirst()
                if (word == endWord) {
                    return ladderLength
                }

                for (j in 0 until word.length) {
                    val newWord = word.substring(0, j) + "*" + word.substring(j + 1)
                    val neighbours = dictionary.getOrDefault(newWord, mutableListOf())
                    if (neighbours.isNotEmpty()) {
                        for (neighbour in neighbours) {
                            if (!visited.contains(neighbour)) {
                                visited.add(neighbour)
                                queue.add(neighbour)
                            }
                        }
                    }
                }
            }
        }

        return 0
    }
}

fun main() {
    println(WordLadder().ladderLength("cat", "sag", mutableListOf("bat","bag","sag","dag","dot")))
    println(WordLadder().ladderLength("cat", "sag", mutableListOf("bat","bag","sat","dag","dot")))

}