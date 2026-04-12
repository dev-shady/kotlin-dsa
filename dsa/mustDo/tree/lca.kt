package dsa.mustDo.tree.lca

fun main() {
    println("Lowest Common Ancestor of two nodes")

    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.right?.left = TreeNode(6)
    root.right?.right = TreeNode(7)
    root.right?.left?.left = TreeNode(8)

    val n1 = root.right?.right
    val n2 = root.right?.left?.left

    val ans = lca(root, n1!!.value, n2!!.value)
    println(ans!!.value)
}

class TreeNode(val value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

// assuming both unique values exist in tree
fun lca(root: TreeNode?, val1: Int, val2: Int): TreeNode? {

    if (root == null) {
        return root
    }

    if (root.value == val1 || root.value == val2) {
        return root
    }

    val left = lca(root.left, val1, val2)
    val right = lca(root.right, val1, val2)

    if (left != null && right != null) {
        // found the lca
        return root
    }

    if (left != null) {
        return left
    }

    if (right != null) {
        return right
    }

    return null
}
