import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines


const val SRC_PATH = "src/main/kotlin"

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: Int) =
    Path("$SRC_PATH/day${day.toDayString()}/Day${day.toDayString()}_values.txt").readLines()

fun readTestInput(day: Int) =
    Path("$SRC_PATH/day${day.toDayString()}/Day${day.toDayString()}_values_test.txt").readLines()

private fun Int.toDayString(): String = this.toString().padStart(2, '0')

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun printTestOutput(testInput: List<String>, solution: Int) {
    printTestOutput(testInput, solution.toLong())
}

fun printTestOutput(testInput: List<String>, solution: Long) {
    println(
        """


        --- Test Results ---------------------------------------------------------
        
        Input: $testInput
        
        Solution (test)
        $solution
        
        --------------------------------------------------------------------------
    """.trimIndent()
    )
}

fun printOutput(part1: Int) {
    printOutput(part1.toLong())
}

fun printOutput(solution: Long) {
    println("\n\n")
    println(
        """
        +++ Results +++
        
        Solution
        $solution
    """.trimIndent()
    )
}

fun Regex.getFirstGroup(line: String): String = this.find(line)!!.groupValues[1]


private val numberRegex = Regex("(\\d+)")
fun findAllNumbers(input: String): List<Long> {
    return numberRegex.findAll(input).map { it.value.toLong() }.toList()
}

fun <T> List<T>.getAllPairs(): List<Pair<T, T>> {
    return this.flatMapIndexed { index, entry ->
        this.drop(index + 1).map { entry to it }
    }
}