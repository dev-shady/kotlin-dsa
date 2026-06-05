package leetcode.set75

/*
What I am thinking is to start from the last node as I move up, swap the noes if required. We will make use of the fact that we already know the position that each node needs to be in for the output. eg. last node at n pos needs to be in 2 position, n-1 th node needs to be in 4 pos. Basically new pos = (nodes position from the last node) * 2 . this is valid only for nodes between middle node and last node. We will use this to figure out if nodes swapping required or not.
The algo:
1. use recursion to go till the last node. as we move back up, we will modify the pointers. since last node is a single node, just return it.
2. now check if returned list head node is at correct position, if not then swap current node with head node.
3. check if current node needs to go more deeper. Check if the new node next to current node is in its correct position, if not swap them. keep doing this until curnode reaches stable position. Return the modified list.
4. do the same for every node as we move back up (as recusrion stack unfolds)
 */
/*
'If curnode needs to push itself deeper into the list to find its stable position, it has to change the .next pointer of the node before it, as well as the nodes it is swapping with.'
no need to change the prev pointer as there is not prev pointer right.
updateList(curNode) {
val newHead = updateList(curNode.next)
//swap curNode with newHead until it reaches stable position
val head = curNode
if (newHead is not in correct position) {
head = newHead
tmp = newHead.next
newHead.next = curNode
curNode.next = tmp
}
//now keep swapping curNode until the list is stable
//don't modify head anymore
 */
/*
The Core Architectural BottleneckIn a singly linked list, when a deeper stack frame changes the head of a sub-list
(e.g., turning 2 -> 3 -> 4 into 4 -> 2 -> 3), the parent node (Node 1) must actively update its .next pointer to point to that new sub-list head (Node 4).
If it skips the block and does nothing, the chain breaks.
The paradox of the pure recursive approach is that to make the first half stable, you must modify the pointers of the first-half nodes
using the returned values from the second half.
 */
/*
if (newHead is not in correct position) {
    head = newHead          // head becomes 4
    tmp = newHead.next      // tmp becomes 2 (since 4 points to 2)
    newHead.next = curNode  // 4 points to 1
    curNode.next = tmp      // 1 points to 2
}
there is no need to do this final swap as we do swapping only if the node belonged to second half (middle node to last). If it is not fro, thr second half, it has already reached its stable position
as I say there is no need to swap but we should update the curNode pointer in this case.
curNode.next = newHead
this should solve the problem.
 */
class ReorderList {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun reorderList(head: ListNode?): Unit {
        /*
            1. find the middle of the list using slow-fast pointer
            2. reverse the second half with its head pointing to the end of the list
            3. merge the first half and second reversed half by interleaving nodes.
         */
        if (head == null) return

        var slowPtr = head
        var fastPtr = head

        while (fastPtr?.next != null && fastPtr.next?.next != null) {
            slowPtr = slowPtr?.next
            fastPtr = fastPtr.next?.next
        }
        //slowPtr now points to the tail of the first half and make its next null
        var reverseListHead = slowPtr?.next
        slowPtr?.next = null

        var next = reverseListHead?.next
        reverseListHead?.next = null
        while (next != null) {
            val newNext = next.next
            next.next = reverseListHead
            reverseListHead = next
            next = newNext
        }

        //merge head and reverseListHead lists
        var curHead = head
        while (reverseListHead != null &&  curHead!=null) {
            val nextOfHead = curHead.next
            val nextOfReverseHead = reverseListHead.next
            curHead.next = reverseListHead
            reverseListHead.next = nextOfHead
            curHead = nextOfHead
            reverseListHead = nextOfReverseHead
        }

    }
}

fun main() {

}


