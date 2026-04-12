package dsa.mustDo.tree.bstFromPreOrder
fun main() {
    println("Construct BST from Pre Order Travsersal")
    val node = buildBST(intArrayOf(10, 5, 1, 7, 40, 50))
    inorderTraversal(node)
}

fun inorderTraversal(node: TreeNode?) {
    if (node == null) {
        return
    }
    inorderTraversal(node.left)
    println(node.value)
    inorderTraversal(node.right)
}

class TreeNode(val value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun buildBSTUtil(preorder: IntArray, low: Int, high: Int): TreeNode? {

    if (low > high) {
        return null
    }

    val parent = TreeNode(preorder[low])

    if (low == high) {
        return parent
    }

    var rightNodeIndex = low + 1
    val parentValue = parent.value

    while (rightNodeIndex <= high) {
        if (preorder[rightNodeIndex] > parentValue) {
            break
        }
        rightNodeIndex++
    }

    parent.left = buildBSTUtil(preorder, low + 1, rightNodeIndex - 1)
    parent.right = buildBSTUtil(preorder, rightNodeIndex, high)

    return parent
}

fun buildBST(preorder: IntArray): TreeNode? {
    return buildBSTUtil(preorder, 0, preorder.size - 1)
}
