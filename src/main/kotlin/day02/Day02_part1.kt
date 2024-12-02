package day02

import printOutput
import printTestOutput
import readInput
import readTestInput

fun main() {
    val day = 2
    val part = 1
    println("\nExecuting part $part of day $day\n\n")

    fun solvePuzzle(input: List<String>): Int {
        return input.size
    }

    val testInput = readTestInput(day)
    val testSolution = solvePuzzle(testInput)

    printTestOutput(testInput, testSolution)

    val input = readInput(day)
    val solution = solvePuzzle(input)

    printOutput(solution)
}