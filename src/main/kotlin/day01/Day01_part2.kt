package day01

import findAllNumbers
import printOutput
import printTestOutput
import readInput
import readTestInput

fun main() {
    val day = 1
    val part = 2
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

        val similarityScores = leftValues.map { left ->
            left * (rightValues.count { right -> right == left })
        }
        return similarityScores.sum()
    }

    val testInput = readTestInput(day)
    val testSolution = solvePuzzle(testInput)

    printTestOutput(testInput, testSolution)

    val input = readInput(day)
    val solution = solvePuzzle(input)

    printOutput(solution)
}
