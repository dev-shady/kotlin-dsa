package leetcode.set75

import kotlin.math.max
import kotlin.math.min

/*
for problem 152. Maximum Product Subarray,
I figured that the problem only comes if there are -ve numbers otherwise the whole array product should be the max possible product.
if there are -ve numbers, the problem only comes if there are odd number of -ve numbers otherwise the whole array product should be the max possible product.
if there are odd number of -ve numbers, we can still get the max product by ignoring 1 of the -ve number so that count becomes even.
but the question is which -ve number. If the chose one (the one we ignore) is in the middle, it will break the array in to two . then we will have to find the left max product and right max product and take the max.
to generalize this,
1. calculate running product of elements from left to right. save them in. a leftProductCache.
2. calculate running product of elements from right to left. save them in. a rightProductCache.
3. now we will see what is the max product possible if we ignore each number .
iterate from left to rightmost number in the array.
find maxproduct possible if current ith number is ignored = max(leftProductCache[i-1],rightProductCache[i+1])
4. find the global max product from this iteration.
5. compare this global max with total product of the array and return the max
*/
/*
    The Bug: Zeros Kill Your Caches:
    missed case with elements 0 in middle like [2, 3, 0, -5, 4]
 */

class MaxProductSubarray {
    fun maxProduct(nums: IntArray): Int {
        var maxProduct = 0
        var negMaxProduct = -1

        val len = nums.size
        val leftMaxProduct = IntArray(len)
        val rightMaxProduct = IntArray(len)

        var curProd = 1

        for (i in 0 until len) {
            if (nums[i] == 0) {
                curProd = 1
                leftMaxProduct[i] = 0
            } else {
                leftMaxProduct[i] = curProd* nums[i]
                curProd = leftMaxProduct[i]
                maxProduct = max(maxProduct, curProd)
                if (curProd < 0) {
                    negMaxProduct = min(negMaxProduct, curProd)
                }
            }
        }

        curProd = 1
        for (i in len-1 downTo  0) {
            if (nums[i] == 0) {
                curProd = 1
                rightMaxProduct[i] = 0
            } else {
                rightMaxProduct[i] = curProd* nums[i]
                curProd = rightMaxProduct[i]
                maxProduct = max(maxProduct, curProd)
                if (curProd < 0) {
                    negMaxProduct = min(negMaxProduct, curProd)
                }
            }
        }
        var isZeroPresent = false
        for (i in 0 until len) {
            if (nums[i] == 0) {
                isZeroPresent = true
                break
            }
        }

        if (maxProduct == 0) {
            if (isZeroPresent) {
                return 0
            } else {
                return negMaxProduct
            }
        }

        return maxProduct
    }
}

fun main() {
    println(MaxProductSubarray().maxProduct(intArrayOf(-2,0,-1)))
}


