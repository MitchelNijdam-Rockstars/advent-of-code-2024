package day01

import findAllNumbers
import kotlin.math.abs
import printOutput
import printTestOutput
import readInput
import readTestInput

fun main() {
    val day = 1
    val part = 1
    println("\nExecuting part $part of day $day\n\n")

    fun parseInput(lines: List<String>): Pair<List<Int>, List<Int>> {
        val leftValues = mutableListOf<Int>()
        val rightValues = mutableListOf<Int>()

        lines.forEach { line ->
            val numbers = findAllNumbers(line)
            leftValues.add(numbers.first().toInt())
            rightValues.add(numbers.last().toInt())
        }

        return leftValues to rightValues
    }

    fun solvePuzzle(input: List<String>): Int {
        val (leftValues, rightValues) = parseInput(input)

        val orderedLeft = leftValues.sorted()
        val orderedRight = rightValues.sorted()

        val leftRightPairs = orderedLeft.zip(orderedRight)

        val distances = leftRightPairs.map { (left, right) -> abs(left - right) }

        return distances.sum()
    }

    val testInput = readTestInput(day)
    val testSolution = solvePuzzle(testInput)

    printTestOutput(testInput, testSolution)

    val input = readInput(day)
    val solution = solvePuzzle(input)

    printOutput(solution)
}
