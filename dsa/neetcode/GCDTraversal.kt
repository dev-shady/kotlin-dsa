package neetcode

class GCDTraversal {

   companion object {
       const val MAX_VAL = 100000
       val loopBoundary = Math.sqrt(MAX_VAL.toDouble()).toInt()
   }

    class DSU (n: Int) {
        val parent = IntArray(n) { it }
        val rank = IntArray(n) { 1 }

        fun find(i: Int): Int {
            if (parent[i] == i) {
                return i
            }

            parent[i] = find (parent[i])
            return parent[i]
        }

        fun union(i: Int, j: Int) {
            val rootI = find(i)
            val rootJ = find(j)

            if (rootI == rootJ) return

            if (rank[rootI] > rank[j]) {
                parent[rootJ] = rootI
            }else if (rank[rootI] < rank[j]) {
                parent[rootI] = rootJ
            } else {
                parent[rootJ] = rootI
                rank[rootI]++
            }

        }

    }

    val spf = IntArray(MAX_VAL+1) { it }

    fun buildSPF() {

        for (i in 2..loopBoundary) {
            if (spf[i] == i) {
                for (j in i*i..MAX_VAL step i) {
                    spf[j] = i
                }
            }
        }
    }

    init {
        buildSPF()
    }

    fun canTraverseAllPairs(nums: IntArray): Boolean {

        val n = nums.size
        val dsu = DSU(n)
        val primeFactors  = Array(n) { ArrayList<Int>() }
        for (i in 0 until n) {
            var temp = nums[i]

            while (temp > 1) {
                var prime = spf[temp]
                primeFactors[i].add(prime)
                while (temp%prime == 0) {
                    temp /= prime
                }
            }
        }

//        println(primeFactors.contentDeepToString())

        val parentOfPrime = IntArray(MAX_VAL+1) { -1 }

        for (i in 0 until n) {
            val primeNumbers = primeFactors[i]
            for (prime in primeNumbers) {
                if (parentOfPrime[prime] == -1) {
                    parentOfPrime[prime] = i
                } else {
                    val parent = parentOfPrime[prime]
                    dsu.union(i, parent)
                }
            }
        }

        val root = dsu.find(0)
        for (i in 1 until n) {
            if (dsu.find(i) != root) {
                return false
            }
        }

        return true
    }
}

fun main(args: Array<String>) {
    println(GCDTraversal().canTraverseAllPairs(intArrayOf(4,3,12)))
    println(GCDTraversal().canTraverseAllPairs(intArrayOf(2,3,7)))
    println(GCDTraversal().canTraverseAllPairs(intArrayOf(100000,99991)))


}