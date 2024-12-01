package day01

import printOutput
import printTestOutput
import readInput
import readTestInput

fun main() {
    val day = 1
    val part = 2
    println("\n\n")

    fun solvePuzzle(input: List<String>): Int {
        return input.size
    }

    val testInput = readTestInput(day, part)
    val testSolution = solvePuzzle(testInput)

    printTestOutput(testInput, testSolution)

    val input = readInput(day, part)
    val solution = solvePuzzle(input)

    printOutput(solution)
}
