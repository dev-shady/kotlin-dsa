package neetcode

class FirstMissingPositive {
    fun firstMissingPositive(nums: IntArray): Int {
        val n = nums.size
        var idx = 0
        var i =0

        while (i < n) {
            val num = nums[i]
            if (num <=0 || num == i+1) {
                i++
                continue
            }

            if (num < n) {
                val tmp = nums[num-1]
                if (tmp == num) {
                    i++
                    continue
                }
                nums[num-1] = num
                nums[i] = tmp
                continue
            }
            i++
        }

        i = 0
        while (i < n) {
            if (nums[i] != i+1 ) {
                return i+1
            }
            i++
        }
        return n+1
    }
}

fun main(args: Array<String>) {
    println(FirstMissingPositive().firstMissingPositive(intArrayOf(-2,-1,0)))
    println(FirstMissingPositive().firstMissingPositive(intArrayOf(1,2,4)))
    println(FirstMissingPositive().firstMissingPositive(intArrayOf(1,2,4,5,6,3,1)))
}