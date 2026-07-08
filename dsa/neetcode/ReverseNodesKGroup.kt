package neetcode

class ReverseNodesKGroup {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {

        var cur = head

        for (i in 0 until k) {
            if (cur == null) {
                return head
            }
            cur = cur.next
        }

        // cur is at head of new subgroup of size k
        val reversedListHead = reverseKGroup(cur, k)
        var prev = reversedListHead

        // now reverse current group and merge it with reversedListHead
        cur = head
        for (i in 0 until k) {
            val next = cur?.next
            cur?.next = prev
            prev = cur
            cur = next
        }

        // the prev is now at the new current head
        return prev
    }
}

fun main(args: Array<String>) {
    val head = ReverseNodesKGroup.ListNode(1)
    head.next = ReverseNodesKGroup.ListNode(2)
    head.next!!.next = ReverseNodesKGroup.ListNode(3)
    head.next!!.next!!.next = ReverseNodesKGroup.ListNode(4)
    head.next!!.next!!.next!!.next = ReverseNodesKGroup.ListNode(5)
    head.next!!.next!!.next!!.next!!.next = ReverseNodesKGroup.ListNode(6)

    var newHead = (ReverseNodesKGroup().reverseKGroup(head, 3))

    while (newHead != null) {
        println(newHead.`val`)
        newHead =  newHead.next
    }
}