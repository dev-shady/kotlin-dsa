package dsa.mustDo.tree.diameter

fun main() {
    println("Diameter of a Tree for two nodes")

    val root: TreeNode = TreeNode(1)
    root.right = TreeNode(2)
    root.right?.left = TreeNode(3)
    root.right?.right = TreeNode(4)
    root.right?.left?.left = TreeNode(5)
    root.right?.right?.right = TreeNode(6)

    val result = Result()
    findDiameter(root, result)
    println(result.maxDiameter)
}

class Result(var maxDiameter: Int = 0)

fun findDiameter(root: TreeNode?, result: Result): Int {

    if (root == null) return -1

    val leftSize = findDiameter(root.left, result) + 1
    val rightSize = findDiameter(root.right, result) + 1
    result.maxDiameter = result.maxDiameter.coerceAtLeast(leftSize + rightSize)
    return leftSize.coerceAtLeast(rightSize)
}

data class TreeNode(val value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
