fun <T> mergeQueues(queue1: QueueGeneric<T>, queue2: QueueGeneric<T>): QueueGeneric<T> {

    // merge two queues
    val mergedQueue = QueueGeneric<T>()
}

fun <T> QueueGeneric<T>.mergeWith(other: QueueGeneric<T>): QueueGeneric<T> {
    val result = QueueGeneric<T>()
    // Use this (the first queue) and other (the second queue)
    return result
}

fun <T> mergeFlexible(q1: QueueGeneric<out T>, q2: QueueGeneric<out T>): QueueGeneric<T> {
    val result = QueueGeneric<T>()
    // Now you can merge a Queue of Strings into a Queue of Any

    while (!q1.isEmpty() && !q2.isEmpty()) {
        var topOfQ1 = q1.peek()
        var topOfQ2 = q2.peek()

        result.add(topOfQ1)
        result.add(topOfQ2)

        q1.remove()
        q2.remove()
    }

    while (!q1.isEmpty()) {
        var topOfQ1 = q1.peek()
        result.add(topOfQ1)
        q1.remove()
    }

    while (!q2.isEmpty()) {
        var topOfQ2 = q2.peek()
        result.add(topOfQ2)
        q2.remove()
    }

    return result
}

fun <T> mergeFlexibleAI(q1: QueueGeneric<out T>, q2: QueueGeneric<out T>): QueueGeneric<T> {
    val result = QueueGeneric<T>()

    // Interleave elements using direct removal
    while (!q1.isEmpty() && !q2.isEmpty()) {
        result.add(q1.remove())
        result.add(q2.remove())
    }

    // Use a single line for draining remaining items
    while (!q1.isEmpty()) result.add(q1.remove())
    while (!q2.isEmpty()) result.add(q2.remove())

    return result
}
