package leetcode.set75

class SumOfTwoPositiveNumbers {

    fun solve(num1: Int, num2: Int): Int {
        val num1Binary = num1.toString(2)
        val num2Binary = num2.toString(2)

        val len1 = num1Binary.length
        val len2 = num2Binary.length
        val len = Math.max(len1, len2)
        var carry = 0
        var sum = 0
        val sb = StringBuilder(100)

        repeat(len) {i ->
            val num1Bit =  num1 shr i and 1
            val num2Bit =  num2 shr i and 1

            if (num1Bit == 1) {
                if (num2Bit == 1) {
                    if (carry == 1) {
                        sb.append('1')
                    } else {
                        sb.append('0')
                    }
                    carry = 1
                } else {
                    if (carry == 1) {
                        sb.append('0')
                        carry = 1
                    } else {
                        sb.append('1')
                        carry = 0
                    }
                }
            } else {
                if (num2Bit == 1) {
                    if (carry == 1) {
                        sb.append('0')
                        carry = 1
                    } else {
                        sb.append('1')
                        carry = 0
                    }
                } else {
                    if (carry == 1) {
                        sb.append('1')
                        carry = 0
                    } else {
                        sb.append('0')
                        carry = 0
                    }
                }
            }
        }

        if (carry == 1) {
            sb.append('1')
        }

        val reversedString = sb.reverse().toString()
        return reversedString.toInt(2)
    }
}

fun main() {
    println(SumOfTwoPositiveNumbers().solve(504849, 989409))
}