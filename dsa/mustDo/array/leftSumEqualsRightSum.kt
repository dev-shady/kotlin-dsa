package mustDo.array
fun main() {
    println("AppsForBharat")
    println(leftSumEqualsRightSum.solve(intArrayOf(1,2,0,3)))
    println(leftSumEqualsRightSum.solve(intArrayOf(1,1,1,1)))
    println(leftSumEqualsRightSum.solve(intArrayOf(-7, 1, 5,   2, -4, 3  , 0)))
}

object leftSumEqualsRightSum {

    fun solve(data: IntArray): Int {
        val size = data.size
        if (size == 0) return -1

        var leftSum = data[0]
        var totalSum = 0
        for (i in 0 until size) {
            totalSum = totalSum + data[i]
        }

        for (i in 1 until size-1) {
            var rightSum = totalSum - (data[i] + leftSum)
            if (leftSum == rightSum) {
                return i
            }
            leftSum += data[i]
        }
        return -1
    }
}