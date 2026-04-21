class QueueGeneric<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    fun add(data: T) {

        if (head == null) {
            head = Node(data)
            tail = head
            return
        }

        tail?.next = Node(data)
        tail = tail?.next
    }

    fun remove(): T {

        if (head == null) {
            throw UnsupportedOperationException("queue is empty")
        }

        if (head == tail) {
            val data = head!!.data
            head = null
            tail = null
            return data
        } else {
            val data = head!!.data
            head = head!!.next
            return data
        }
    }

    fun peek(): T {
        if (head == null) {
            throw UnsupportedOperationException("queue is empty")
        }

        return head!!.data
    }

    fun isEmpty(): Boolean {
        if (head == null) {
            return true
        } else {
            return false
        }
    }

    private class Node<T>(var data: T, var next: Node<T>? = null)
}

class QueueGeneric2<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    fun add(data: T) {
        val newNode = Node(data)
        val currentTail = tail

        if (currentTail == null) {
            head = newNode
            tail = newNode
        } else {
            currentTail.next = newNode
            tail = newNode
        }
    }

    fun remove(): T {
        // 1. Capture head in a local variable for thread-safety and smart-casting
        val currentHead = head ?: throw NoSuchElementException("Queue is empty")

        val data = currentHead.data
        head = currentHead.next

        // 2. If the queue is now empty, reset the tail
        if (head == null) {
            tail = null
        }

        return data
    }

    fun peek(): T {
        // 3. Use the Elvis operator for concise error handling
        return head?.data ?: throw NoSuchElementException("Queue is empty")
    }

    // 4. Single-expression function for better readability
    fun isEmpty(): Boolean = head == null

    private class Node<T>(var data: T, var next: Node<T>? = null)
}

fun main() {
    val que = QueueGeneric<String>()
    que.add("5")
    println(que.peek())
}
