package dsa.mustDo.tree.maxNonAdjacentSum

class MaxNonAdjacentSum {

    fun findMaxNonAdjacentSum(root: TreeNode): Int {
        return findMaxNonAdjacentSumHelper(root, HashMap())
    }

    fun findMaxNonAdjacentSumHelper(root: TreeNode?, cache: HashMap<TreeNode, Int>): Int {

        if (root == null) {
            return 0
        }
        if (cache.containsKey(root)) {
            return cache[root]!!
        }

        var included = root.value
        if (root.left != null) {
            included +=
                    (findMaxNonAdjacentSumHelper(root.left?.left, cache) +
                            findMaxNonAdjacentSumHelper(root.left?.right, cache))
        }

        if (root.right != null) {
            included +=
                    (findMaxNonAdjacentSumHelper(root.right?.left, cache) +
                            findMaxNonAdjacentSumHelper(root.right?.right, cache))
        }

        val excluded =
                findMaxNonAdjacentSumHelper(root.left, cache) +
                        findMaxNonAdjacentSumHelper(root.right, cache)

        cache[root] = maxOf(included, excluded)
        return cache[root] ?: 0
    }
}

class TreeNode(val value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun main() {
    val root: TreeNode = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.right?.left = TreeNode(4)
    root.right?.right = TreeNode(5)
    root.left?.left = TreeNode(1)

    println(MaxNonAdjacentSum().findMaxNonAdjacentSum(root))
}
