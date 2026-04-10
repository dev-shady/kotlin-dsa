fun main() {
    println("Maximum Bipartite Matching")
    println("M Applications and N Jobs")

    val bpGraph =
            arrayOf(
                    booleanArrayOf(false, true, true, false, false, false),
                    booleanArrayOf(true, false, false, true, false, false),
                    booleanArrayOf(false, false, true, false, false, false),
                    booleanArrayOf(false, false, true, true, false, false),
                    booleanArrayOf(false, false, false, false, false, false),
                    booleanArrayOf(false, false, false, false, false, true)
            )
    println("max matching possible is ${maxBipartiteMatching(bpGraph, 6, 6)}")
}

fun isAssignmentPossible(
        graph: Array<BooleanArray>,
        applicant: Int,
        seen: BooleanArray,
        jobAssignment: IntArray,
        n: Int
): Boolean {

    // loop over all the jobs
    for (i in 0 until n) {
        if (seen[i] || !graph[applicant][i]) {
            continue
        }
        if (jobAssignment[i] < 0) {
            // job not assigned to anyone yet
            jobAssignment[i] = applicant
            return true
        }

        // find the applicant the job is assigned to
        // and see he can be assigned another job
        // so that current applicant can get this one
        val assignedTo = jobAssignment[i]
        seen[i] = true
        if (isAssignmentPossible(graph, assignedTo, seen, jobAssignment, n)) {
            jobAssignment[i] = applicant
            return true
        }
    }
    return false
}

fun maxBipartiteMatching(graph: Array<BooleanArray>, m: Int, n: Int): Int {
    // m applicants
    // n jobs
    var cur_matches = 0
    val jobAssignment = IntArray(n) { -1 } // no job is assigned to anyone

    for (i in 0 until (m)) {
        val seen = BooleanArray(n)
        if (isAssignmentPossible(graph, i, seen, jobAssignment, n)) {
            cur_matches++
        }
    }

    return cur_matches
}
