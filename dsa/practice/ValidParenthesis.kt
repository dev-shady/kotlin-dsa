package practice.stack

import java.util.Stack

class ValidParenthesis {
    fun isValid(s: String): Boolean {

        val stack = Stack<Char>()
        val closedBraces = hashMapOf(')' to '(', '}' to '{', ']' to '[')
        val openBraces = hashMapOf('(' to ')', '[' to ']', '{' to '}')

        for (i in 0 until s.length) {
            if (openBraces.contains(s[i])) {
                stack.push(s[i])
            } else if (closedBraces.contains(s[i])) {
                if (stack.isEmpty()) {
                    return@isValid false
                }
                val top = stack.pop()
                if (openBraces[top] != s[i]) {
                    return@isValid false
                }
            } else {
                return@isValid false
            }
        }

        if (stack.isEmpty()) {
            return true
        } else {
            return false
        }
    }
}

fun main() {
    println(ValidParenthesis().isValid("()[]{}"))
    println(ValidParenthesis().isValid("[]"))
    println(ValidParenthesis().isValid("([{}])"))
}
