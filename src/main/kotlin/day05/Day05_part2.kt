package day05

import getAllPairs
import printOutput
import printTestOutput
import readInput
import readTestInput

fun main() {
    val day = 5
    val part = 1
    println("\nExecuting part $part of day $day\n\n")

    fun parseInput(input: List<String>): Instruction {
        val orderings = mutableListOf<Pair<Int, Int>>()
        var counter = 0
        for (line in input) {
            counter++
            if (line == "") break

            val (left, right) = line.split("|").map { it.toInt() }

            orderings.add(left to right)
        }

        val manuals = (counter..input.size - 1).map { index ->
            input[index].split(",").map { it.toInt() }
        }

        return Instruction(orderings, manuals)
    }

    fun isCorrectlyOrdered(manual: List<Int>, orderings: List<Pair<Int, Int>>): Boolean {
        // since all pairs are based on order (left is always to the left of right)
        // we can check there is no opposite ordering
        return manual.getAllPairs().none { (left, right) ->
            orderings.any { ordering -> ordering.first == right && ordering.second == left }
        }
    }

    fun order(
        unordered: List<Int>,
        orderings: List<Pair<Int, Int>>
    ): List<Int> {
        val moreOrdered = unordered.toMutableList()
        val pairs = unordered.getAllPairs()

        pairs.forEach { (left, right) ->
            if (orderings.any { it.first == right && it.second == left }) { // when an opposite order is found, swap
                val leftIndex = moreOrdered.indexOf(left)
                val rightIndex = moreOrdered.indexOf(right)
                moreOrdered[leftIndex] = right
                moreOrdered[rightIndex] = left
            }
        }

        return if (!isCorrectlyOrdered(moreOrdered, orderings)) {
            order(moreOrdered, orderings)
        } else {
            moreOrdered
        }
    }

    fun solvePuzzle(input: List<String>): Int {
        val instructions = parseInput(input)

        return instructions.manuals
            .filter { manual -> !isCorrectlyOrdered(manual, instructions.orderings) }
            .map { manual -> order(manual, instructions.orderings) }
            .sumOf { manual -> manual[manual.size / 2] }
    }

    val testInput = readTestInput(day)
    val testSolution = solvePuzzle(testInput)

    printTestOutput(testInput, testSolution)

    val input = readInput(day)
    val solution = solvePuzzle(input)

    printOutput(solution)
}