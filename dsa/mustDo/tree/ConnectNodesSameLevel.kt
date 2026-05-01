package mustDo.tree

class ConnectNodesSameLevel {

    internal fun getNextRight(root: Node): Node? {
        var tmp = root.nextRight
        while (tmp != null) {
            if (tmp.left != null) {
                return tmp.left
            }
            if (tmp.right != null) {
                return tmp.right
            }

            tmp = tmp.nextRight
        }
        return null
    }

    internal  fun connect(root: Node?) {

        var firstNodeOfLevel = root
        if (root == null) {
            return
        }

        while (firstNodeOfLevel != null) {
            var tmp = firstNodeOfLevel
            //traverse whole level starting with root as first node of the lvl
            // and connect all their childrens at same level
            //given this level is already connected
            //which is true for base case having only 1 node root
            while (tmp != null) {
                if (tmp.left != null) {
                    if (tmp.right != null) {
                        tmp.left!!.nextRight = tmp.right
                    } else {
                        tmp.left!!.nextRight = getNextRight(tmp)
                    }
                }
                tmp = tmp.nextRight
            }

            if (firstNodeOfLevel.left != null) {
                firstNodeOfLevel = firstNodeOfLevel.left
            } else if (firstNodeOfLevel.right != null) {
                firstNodeOfLevel = firstNodeOfLevel.right
            } else {
                firstNodeOfLevel = firstNodeOfLevel.nextRight
            }
        }
    }
}

internal class Node(val value: Int) {
    var left: Node? = null
    var right: Node? = null
    var nextRight: Node? = null
}
fun main(args: Array<String>) {
    println("Connect Nodes at Same Level")
    val root = Node(10)
    root.left = Node(8)
    root.right = Node(2)
    root.left!!.left = Node(3)
    root.right!!.left = Node(4)
    ConnectNodesSameLevel().connect(root)

    println("Next Right of 8 is ${root.left!!.nextRight?.value}");
    println("Next Right of 3 is ${root.left!!.left?.nextRight?.value}");
}