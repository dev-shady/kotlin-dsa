package mustDo.tree

class MinDistanceNodes {

    internal class TreeNode(val value: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    private fun lca(root: TreeNode?, val1: Int, val2: Int): TreeNode? {

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

    internal fun minDistance(root: TreeNode?, val1: Int, val2: Int): Int {

        val height = mutableMapOf<Int, Int>()
        calculateHeight(root, 0, height)
        val lcaNode = lca(root, val1, val2)

        if (lcaNode == null) {
            return -1
        }

        if (!height.containsKey(val1) || !height.containsKey(val2) || !height.containsKey(lcaNode.value)) {
            return -1
        }

        return height[val1]!! + height[val2]!! - 2*height[lcaNode.value]!!

    }

    private fun calculateHeight(root: TreeNode?, currentHeight : Int, height: MutableMap<Int, Int>) {
        if (root == null) {
            return
        }

        height[root.value] = currentHeight
        calculateHeight(root.left, currentHeight + 1, height)
        calculateHeight(root.right, currentHeight + 1, height)
    }
}

fun main(args: Array<String>) {
    println("Min distance between two nodes of a Binary Tree")
    val root: MinDistanceNodes.TreeNode = MinDistanceNodes.TreeNode(1)
    root.left = MinDistanceNodes.TreeNode(2)
    root.right = MinDistanceNodes.TreeNode(3)
    root.left?.left = MinDistanceNodes.TreeNode(4)
    root.left?.right = MinDistanceNodes.TreeNode(5)
    root.right?.left = MinDistanceNodes.TreeNode(6)
    root.right?.right = MinDistanceNodes.TreeNode(7)

    val a = 4
    val b = 7

    println(MinDistanceNodes().minDistance(root, 4, 7))
}