package day04

import printOutput
import printTestOutput
import readInput
import readTestInput


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

        val ms: List<Move> = Move.entries.mapNotNull { movement ->
            val x = startCoordinates.x + movement.deltaX
            val y = startCoordinates.y + movement.deltaY
            if (isOutsideBox(x, width - 1) || isOutsideBox(y, height - 1)) return@mapNotNull null
            if (input[y][x] == 'M') {
                return@mapNotNull movement
            }
            null
        }
        if (ms.count() != 2) return 0

        for (movement in ms) {
            val opposite = movement.getOpposite()
            val x = startCoordinates.x + opposite.deltaX
            val y = startCoordinates.y + opposite.deltaY
            if (isOutsideBox(x, width - 1) || isOutsideBox(y, height - 1)) return 0
            if (input[y][x] == 'M') return 0
        }

        val ss = Move.entries.count { movement ->
            val x = startCoordinates.x + movement.deltaX
            val y = startCoordinates.y + movement.deltaY
            if (isOutsideBox(x, width - 1) || isOutsideBox(y, height - 1)) return@count false
            input[y][x] == 'S'
        }
        if (ss != 2) return 0

        return 1
    }

    fun solvePuzzle(input: List<String>): Int {
        val startCoordinates = input.mapIndexed { y, line ->
            line.mapIndexed { x, ch -> if (ch == 'A') StartCoordinates(x, y) else null }.filterNotNull()
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

private enum class Move(val deltaX: Int, val deltaY: Int) {
    TL(-1, -1),
    TR(1, -1),
    BL(-1, 1),
    BR(1, 1);

    fun getOpposite(): Move {
        return when (this) {
            TL -> BR
            TR -> BL
            BL -> TR
            BR -> TL
        }
    }
}