package day08

import printOutput
import printTestOutput
import readInput
import readTestInput

fun main() {
    val day = 8
    val part = 1
    println("\nExecuting part $part of day $day\n\n")


    data class GridLocation(
        val x: Int,
        val y: Int
    ) {
        fun isFirst(other: GridLocation): Boolean {
            return this.y < other.y || (this.y == other.y && this.x < other.x)
        }

        fun getIfInsideGrid(width: Int, height: Int): GridLocation? {
            return if (this.x < 0 || this.y < 0 || this.x >= width || this.y >= height) null else this
        }
    }

    data class Antenna(
        val frequency: Char,
        val location: GridLocation
    )

    fun parseInput(input: List<String>): List<Antenna> {
        return input.flatMapIndexed { y, line ->
            line.mapIndexed { x, place ->
                if (place == '.') return@mapIndexed null
                Antenna(
                    frequency = place,
                    GridLocation(x, y)
                )
            }.filterNotNull()
        }
    }

    fun getPairs(antennas: List<Antenna>): List<Pair<Antenna, Antenna>> {
        return antennas.flatMapIndexed { index, antenna ->
            antennas.drop(index + 1).map { antenna to it }
        }
    }

    fun caculateAntinodes(antennas: List<Pair<Antenna, Antenna>>, width: Int, height: Int): List<GridLocation> {
        return antennas.flatMap { (antenna1, antenna2) ->
            val (firstAntenna, secondAntenna) = if (antenna1.location.isFirst(antenna2.location)) antenna1 to antenna2 else antenna2 to antenna1
            val deltaX = secondAntenna.location.x - firstAntenna.location.x
            val deltaY = secondAntenna.location.y - firstAntenna.location.y


            val antinode1 = GridLocation(
                x = firstAntenna.location.x - deltaX,
                y = firstAntenna.location.y - deltaY
            )
            val antinode2 = GridLocation(
                x = secondAntenna.location.x + deltaX,
                y = secondAntenna.location.y + deltaY
            )

            listOfNotNull(
                antinode1.getIfInsideGrid(width, height),
                antinode2.getIfInsideGrid(width, height),
            )
        }
    }

    fun solvePuzzle(input: List<String>): Int {
        val width = input.first().length
        val height = input.size

        val antennas = parseInput(input)
        val antennaGroups = antennas.groupBy { it.frequency }
        return antennaGroups.flatMap { (_, antennas) ->
            val antennaPairs = getPairs(antennas)
            val antinodes = caculateAntinodes(antennaPairs, width, height)
            antinodes
        }.distinct().count()
    }

    val testInput = readTestInput(day)
    val testSolution = solvePuzzle(testInput)

    printTestOutput(testInput, testSolution)

    val input = readInput(day)
    val solution = solvePuzzle(input)

    printOutput(solution)


}
