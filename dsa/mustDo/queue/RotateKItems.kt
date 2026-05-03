package mustDo.queue

class RotateKItems {

    internal fun rotateK(queue: ArrayDeque<Int>, k: Int) {
        val size = queue.size
        moveKitems(queue, k)
        repeat(size-k) {
            queue.addLast(queue.removeFirst())
        }

    }

    internal fun moveKitems(queue: ArrayDeque<Int>, k: Int) {
        if (k == 0) {
            return
        }
        val item = queue.removeFirst()
        moveKitems(queue, k-1)
        queue.addLast(item)
    }
}

fun main() {
    println(" Rotate K Items")
    val queue = ArrayDeque<Int>()
    queue.addLast(1)
    queue.addLast(2)
    queue.addLast(3)
    queue.addLast(4)
    queue.addLast(5)
    RotateKItems().rotateK(queue, 3)
    println(queue.joinToString(" "))
}