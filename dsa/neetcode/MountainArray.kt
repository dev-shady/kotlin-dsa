package neetcode

interface MountainArray {
    fun get(index: Int): Int
    fun length(): Int
}

class MountainArrayTest {

    val cache = HashMap<Int, Int>()

    fun findInMountainArray(target: Int, mountainArr: MountainArray): Int {
        println("findInMountainArray $target ${mountainArr.length()}")
        var index = -1

        // find the pivot element first
        var low = 0
        var size = mountainArr.length()
        var high = size-1
        var mid = low
        var pivot = -1

        while (low <= high) {
            println("$low, $high")
            if (low == high && high == size-1) {
                println("low high and size equal, aborting..")
                break
            }
            mid = (low + high)/2
            var first = cache.getOrDefault(mid, mountainArr.get(mid))
            if (!cache.containsKey(mid)) { cache[mid] = first}

            var second = cache.getOrDefault(mid+1, mountainArr.get(mid+1))
            if (!cache.containsKey(mid+1)) { cache[mid+1] = second}

            println("mid $mid $first, $second")

            if (first > second) {
                // this could be pivot or in first half
                pivot = mid
                high = mid - 1
            } else if (first < second) {
                //still increasing, pivot lies in other half
                low = mid + 1
            } else if (first == second) {
                println("two numbers equal, aborting..")
                break
            }

        }

        println("pivot $pivot")

        var pivotValue = cache.getOrDefault(pivot, mountainArr.get(pivot))
        if (!cache.containsKey(pivot)) { cache[pivot] = pivotValue}
        if (target == pivotValue) {
            return pivot
        }
        //we have the pivot, find target first in first half for minimum index

        low = 0
        high = pivot-1

        while (low <= high) {
            println("$low, $high first half search")
            mid = (low + high)/2
            var midValue = cache.getOrDefault(mid, mountainArr.get(mid))
            if (!cache.containsKey(mid)) { cache[mid] = midValue}

            if (midValue == target) {
                return mid
            } else if (midValue < target) {
                low = mid + 1
            } else
                high = mid - 1
        }

        //find target in second half

        low = pivot+1
        high = size-1

        while (low <= high) {
            println("$low, $high second half search")
            mid = (low + high)/2
            var midValue = cache.getOrDefault(mid, mountainArr.get(mid))
            if (!cache.containsKey(mid)) { cache[mid] = midValue}

            if (midValue == target) {
                return mid
            } else if (midValue < target) {
                high = mid - 1
            } else
                low = mid + 1
        }

        return -1
    }
}

class MountainArrayImpl(val data : List<Int>): MountainArray {
//    val data = listOf<Int>(2,4,5,2,1)
//    val data = listOf<Int>(1,2,3,4,2,1)

    override fun get(index: Int): Int {
        return data[index]
    }

    override fun length(): Int {
    return data.size
    }
}

fun main() {
    println("index ${MountainArrayTest().findInMountainArray(0, MountainArrayImpl(listOf(1,2,5,1)))}")
    println(MountainArrayTest().findInMountainArray(4, MountainArrayImpl(listOf(1,2,3,4,2,1))))
    MountainArrayTest().findInMountainArray(2, MountainArrayImpl(listOf(1,2,3,4,2,1)))
    MountainArrayTest().findInMountainArray(2, MountainArrayImpl(listOf(10,11,13,15,16,17,19,1)))
    MountainArrayTest().findInMountainArray(2, MountainArrayImpl(listOf(12,11,10)))
}