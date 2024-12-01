import java.io.File

fun main() {
    // read day number from command line
    print("\nEnter day number: ")
    val day = readln().toInt()

    println("Creating files for day $day...")

    // day to string with leading zero if needed
    val dayString = day.toString().padStart(2, '0')

    // create directory
    val directory = File("$SRC_PATH/day$dayString")

    // copy files from the template directory dayXX
    File("$SRC_PATH/dayXX").copyRecursively(directory)

    // rename files, XX should be replaced with day number
    directory.walkTopDown().forEach { file ->
        file.renameTo(File(file.path.replace("XX", dayString)))
    }

    // inside dayXX.kt, replace line 8 with "val day = XX" (and then replace XX with day number)
    listOf(
        "$SRC_PATH/day$dayString/day${dayString}_part1.kt",
        "$SRC_PATH/day$dayString/day${dayString}_part2.kt"
    ).forEach { kotlinFile ->
        File(kotlinFile).writeText(
            File(kotlinFile).readText()
                .replace("val day = 0", "val day = $day")
                .replace("dayXX", "day$dayString")
        )
    }

    println("Done")
}