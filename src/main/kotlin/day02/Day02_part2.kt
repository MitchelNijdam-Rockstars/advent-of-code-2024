package day02

import kotlin.math.abs
import printOutput
import printTestOutput
import println
import readInput
import readTestInput

fun main() {
    val day = 2
    val part = 2
    println("\nExecuting part $part of day $day\n\n")


    fun parseInput(input: List<String>): List<List<Int>> {
        return input.map { line ->
            line.split(' ').map { it.toInt() }
        }
    }

    fun isSafeReport(report: List<Int>, shouldCheckSublist: Boolean = true): Boolean {
        val windowed = report.windowed(2, 1)
        var isDescending: Boolean? = null
        var isSafe = true

        windowed.forEachIndexed { index, (l, r) ->
            if (abs(l - r) < 1 || abs(l - r) > 3) isSafe = false
            if (index == 0) isDescending = l - r < 0
            if (isDescending == true && l - r > 0) isSafe = false
            if (isDescending == false && l - r < 0) isSafe = false
        }

        if (!isSafe && shouldCheckSublist) {
            val news = report.mapIndexed { index, _ ->
                val mutableReport = report.toMutableList()
                mutableReport.removeAt(index)
                mutableReport
            }
            news.println()
            return news.any { newReport -> isSafeReport(newReport, false) }
        }

        return isSafe
    }

    fun solvePuzzle(input: List<String>): Int {
        val levelsPerReport = parseInput(input)
        val safeReports = levelsPerReport.count { isSafeReport(it) }
        return safeReports
    }

    val testInput = readTestInput(day)
    val testSolution = solvePuzzle(testInput)

    printTestOutput(testInput, testSolution)

    val input = readInput(day)
    val solution = solvePuzzle(input)

    printOutput(solution)
}
