package mustDo.tree.PairWithSumInBST

class PairWithSumInBST {

    val sortedTree = ArrayList<Int>()
    fun inorderTraversal(root: Node?) {
        if (root == null) return
        inorderTraversal(root.left)
        sortedTree.add(root.data)
        inorderTraversal(root.right)
    }

    fun findPair(root: Node, target: Int) : Boolean {

        inorderTraversal(root)
        val size = sortedTree.size
        var leftIndex = 0
        var rightIndex = size-1

        //two pointer search
        while (leftIndex < rightIndex) {
            if (sortedTree[leftIndex] + sortedTree[rightIndex] == target) {
                return true
            } else if (sortedTree[leftIndex] + sortedTree[rightIndex] < target) {
                leftIndex++
            } else {
                rightIndex--
            }
        }

        return false
    }
}

class Node(val data: Int) {
    var left: Node? = null
    var right: Node? = null
}

fun main() {
    println("Pair with given sum in a Balanced BST")
    val root = Node(15)
    root.left = Node(10)
    root.right = Node(20)
    root.left!!.left = Node(8)
    root.left!!.right = Node(12)
    root.right!!.left = Node(16)
    root.right!!.right = Node(25)

    val target = 35

    println(PairWithSumInBST().findPair(root, target))
}