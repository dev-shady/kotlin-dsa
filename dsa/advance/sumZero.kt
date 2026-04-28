fun sumZero(input: List<Int>): Pair<Int, Int>? {

    val sorted = input.sorted()

    /*
    sorted method declaration:

    fun <T : Comparable<T>> Iterable<T>.sorted(): List<T> {}

    */

    var first = 0
    var last = sorted.size - 1

    while (first < last && sorted[first] <= 0) {
        if (sorted[first] + sorted[last] == 0) {
            break
        } else if (sorted[first] + sorted[last] < 0) {
            first++
        } else {
            last--
        }
    }

    if (first < last) {
        return Pair(sorted[first], sorted[last])
    } else {
        return null
    }
}

fun sumZero2(input: List<Int>): Pair<Int, Int>? {
    if (input.size < 2) return null

    val sorted = input.sorted()
    input.sortedWith({ a, b -> b - a })

    var first = 0
    var last = sorted.size - 1

    while (first < last) {
        val left = sorted[first]
        val right = sorted[last]
        val sum = left + right

        when {
            sum == 0 -> return Pair(left, right)
            sum < 0 -> first++
            else -> last--
        }

        if (sorted[first] > 0) break
    }

    return null
}

fun main() {
    println(sumZero(listOf(-10, -7, -3, -2, -1, 1, 5)))
}
