package neetcode

import java.util.PriorityQueue

class IPO {

    data class IPOEntity(val capital: Int, val profit: Int)

    fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
        var maxCapital = w
        val size = profits.size
        val ipoEntityList = ArrayList<IPOEntity>()

        for (i in capital.indices) {
            val entity = IPOEntity(capital[i], profits[i])
            ipoEntityList.add(entity)
        }

        ipoEntityList.sortWith { entity1, entity2 ->  entity1.capital - entity2.capital}

        var currenK = 0
        var curIndex = 0
        var captial = w
        val maxHeap = PriorityQueue<IPOEntity>(capital.size) {a,b -> b.profit - a.profit }

        while (currenK < k) {

            while (curIndex < size && ipoEntityList[curIndex].capital <= captial) {
                maxHeap.add(ipoEntityList[curIndex])
                curIndex++
            }
            if (maxHeap.isEmpty()) {
                return maxCapital
            }

            val item = maxHeap.poll()
            maxCapital += item.profit
            captial += item.profit
            currenK++
        }

        return maxCapital
    }
}

fun main(args: Array<String>) {
    println(IPO().findMaximizedCapital(3,0,intArrayOf(1,4,2,3), intArrayOf(0,3,1,1)))
    println(IPO().findMaximizedCapital(4,2,intArrayOf(2,3,1,5,3), intArrayOf(4,4,2,3,3)))

}