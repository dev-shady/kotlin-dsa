object SortHelper {

    public fun bubbleSort(numbers: List<Int>): List<Int> {
        val numberArray = numbers.toIntArray() // val sorted = list.toMutableList() , also possible
        val size = numberArray.size

        (size - 1 downTo 1).forEach { i ->
            (1..i).forEach { j ->
                // after 1st iteration the biggest number should be at size-1 pos
                if (numberArray[j] < numberArray[j - 1]) {
                    // swap and bring the bigger number at higher index
                    val tmp = numberArray[j - 1]
                    numberArray[j - 1] = numberArray[j]
                    numberArray[j] = tmp
                }
            }
        }

        return numberArray.toList()
    }

    public fun selectionSort(numbers: List<Int>): List<Int> {
        val numberArray = numbers.toIntArray()
        /*
        1. i=0, iterate from 0 to size. find the min num idex and place it at 0 pos.
        2. i=1, iterate from 1 to size. find the min num idex and place it at 1 pos.
        Do it till i = size -1
        Repeat and list will be sorted
        */
        return numberArray.toList()
    }

    public fun insertionSort(numbers: List<Int>): List<Int> {
        val numberArray = numbers.toIntArray()
        return numberArray.toList()
    }

    private fun merge(sortedLeft: List<Int>, sortedRight: List<Int>): List<Int> {
        val size = sortedLeft.size + sortedRight.size
        val sortedRes = IntArray(size)

        var leftIndex = 0
        var rightIndex = 0
        var resIndex = 0

        while (leftIndex < sortedLeft.size && rightIndex < sortedRight.size) {
            if (sortedLeft[leftIndex] < sortedRight[rightIndex]) {
                sortedRes[resIndex++] = sortedLeft[leftIndex++]
            } else {
                sortedRes[resIndex++] = sortedRight[rightIndex++]
            }
        }

        if (leftIndex != sortedLeft.size) {
            while (leftIndex < sortedLeft.size) {
                sortedRes[resIndex++] = sortedLeft[leftIndex++]
            }
        }

        if (rightIndex != sortedRight.size) {
            while (rightIndex < sortedRight.size) {
                sortedRes[resIndex++] = sortedRight[rightIndex++]
            }
        }

        return sortedRes.toList()
    }

    private fun mergeSortHelper(start: Int, end: Int, numbers: List<Int>): List<Int> {

        if (start > end) {
            return listOf()
        }

        if (start == end) {
            return listOf(numbers[start])
        }

        val mid = (start + end) / 2

        val sortedLeft = mergeSortHelper(start, mid, numbers)
        val sortedRight = mergeSortHelper(mid + 1, end, numbers)

        val sortedList = merge(sortedLeft, sortedRight)

        return sortedList
    }

    public fun mergeSort(numbers: List<Int>): List<Int> {
        return mergeSortHelper(0, numbers.size - 1, numbers)
    }
}

fun main() {
    // println(SortHelper.bubbleSort(listOf(5, 4, 4, 3, 2, 1, 1)))
    println(SortHelper.mergeSort(listOf(5, 4, 4, 3, 99, 2, 1, 1)))
}
