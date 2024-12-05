package day04

import java.nio.file.Files.move
import printOutput
import printTestOutput
import readInput
import readTestInput

private const val X_MAS = "XMAS"

fun main() {
    val day = 4
    val part = 1
    println("\nExecuting part $part of day $day\n\n")

    fun isOutsideBox(value: Int, max: Int): Boolean {
        return value < 0 || value > max
    }

    fun countXmas(startCoordinates: StartCoordinates, input: List<String>): Int {
        val width = input.first().length
        val height = input.size

        val movementToValid = Movement.entries.map { movement ->
            var currentX = startCoordinates.x
            var currentY = startCoordinates.y

            for (char in X_MAS) {
                if (isOutsideBox(currentX, width - 1) || isOutsideBox(currentY, height - 1))
                    return@map movement to false

                val inputChar = input[currentY][currentX]
                val isEqual = inputChar == char
                if (!isEqual) return@map movement to false
                currentX = currentX + movement.deltaX
                currentY = currentY + movement.deltaY

            }
            movement to true
        }

        val count = movementToValid.toMap().values.count { it == true }
        println("for $startCoordinates, count = $count, $movementToValid")
        return count
    }

    fun solvePuzzle(input: List<String>): Int {
        val startCoordinates = input.mapIndexed { y, line ->
            line.mapIndexed { x, ch -> if (ch == 'X') StartCoordinates(x, y) else null }.filterNotNull()
        }.flatten()

        return startCoordinates.sumOf { coordinates ->
            countXmas(coordinates, input)
        }
    }

    val testInput = readTestInput(day)
    val testSolution = solvePuzzle(testInput)

    printTestOutput(testInput, testSolution)

    val input = readInput(day)
    val solution = solvePuzzle(input)

    printOutput(solution)
}

data class StartCoordinates(
    val x: Int,
    val y: Int
)

enum class Movement(val deltaX: Int, val deltaY: Int) {
    HOR_RIGHT(1, 0),
    HOR_LEFT(-1, 0),
    DIA_TL(-1, -1),
    DIA_TR(1, -1),
    DIA_BL(-1, 1),
    DIA_BR(1, 1),
    VER_TOP(0, -1),
    VER_BOTTOM(0, 1);
}