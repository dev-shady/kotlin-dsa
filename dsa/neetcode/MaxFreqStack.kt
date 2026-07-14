package neetcode

import java.util.Stack
import kotlin.math.max

class MaxFreqStack {

    val freqStack = HashMap<Int, Stack<Int>>()
    val freqMap = HashMap<Int, Int>()
    var maxFreq = 0

    fun push(`val`: Int) {
        val freq = freqMap.getOrPut(`val`) { 0 } + 1
        freqMap[`val`] = freq
        maxFreq = max(maxFreq, freq)
        freqStack.getOrPut(freq) { Stack() }.push(`val`)
    }

    fun pop(): Int {
        val popped = freqStack[maxFreq]!!.pop()
        if (freqStack[maxFreq]!!.isEmpty()) {
            maxFreq--
        }
        freqMap[popped] = freqMap[popped]!! - 1
        return popped
    }
}

fun main(args: Array<String>) {
    val stack = MaxFreqStack()
    stack.push(5)
    stack.push(7)
    stack.push(5)
    stack.push(7)
    stack.push(4)
    stack.push(5)
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())

}