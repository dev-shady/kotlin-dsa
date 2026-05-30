// Time complexity: O(n)
// Generate digit frequency map for each integer and compare them
private object DigitFreq {
    private fun equalDigitFrequency(i1: Int, i2: Int): Boolean {
        val i1Str = i1.toString()
        val i2Str = i2.toString()

        if (i1Str.length != i2Str.length) {
            return false
        }

        val frequencyCounter1 = i1Str.groupingBy { it }.eachCount()
        val frequencyCounter2 = i2Str.groupingBy { it }.eachCount()
        return frequencyCounter1 == frequencyCounter2
    }

    fun getDuplicatedArguments(vararg strings: String): List<String>? =
            strings.groupingBy { it }.eachCount().filter { it.value != 1 }.map { it.key }
}
