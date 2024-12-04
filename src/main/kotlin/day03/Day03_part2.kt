package day03

import printOutput
import printTestOutput
import println
import readInput
import readTestInput

fun main() {
    val day = 3
    val part = 1
    println("\nExecuting part $part of day $day\n\n")

    fun parseInput(input: List<String>): List<Pair<Int, Int>> {
        val regex = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)|don't\\(\\)|do\\(\\)")
        var enabled = true

        return input.flatMap { line ->
            regex.findAll(line).mapNotNull { match ->
                if (match.value == "don't()") {
                    enabled = false
                    return@mapNotNull null
                }
                if (match.value == "do()") {
                    enabled = true
                    return@mapNotNull null
                }
                if (!enabled) return@mapNotNull null

                match.groupValues.println()
                return@mapNotNull match.groupValues[1].toInt() to match.groupValues[2].toInt()
            }
        }
    }

    fun solvePuzzle(input: List<String>): Long {
        val multiplyPairs = parseInput(input)
        return multiplyPairs.sumOf { (l, r) -> l.toLong() * r.toLong() }
    }

    val testInput = readTestInput(day)
    val testSolution = solvePuzzle(testInput)

    printTestOutput(testInput, testSolution)

    val input = readInput(day)
    val solution = solvePuzzle(input)

    printOutput(solution)
}
