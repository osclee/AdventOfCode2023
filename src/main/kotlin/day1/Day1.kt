package day1

import common.getLines;

fun main() {
    part1()
    part2()
}

fun part1() {
    val lines = getLines("src/main/resources/day1/day1.txt")

    var sum = 0;
    for (line in lines) {
        val nums = line.filter { it.isDigit() }

        val firstDigit = nums[0]
        val lastDigit = nums.last()

        val value = "$firstDigit$lastDigit".toInt()

        sum += value
    }

    println("Part 1 Result = $sum")
}

fun part2() {
    val lines = getLines("src/main/resources/day1/day1.txt")
    val regex = Regex("(?=(0|1|2|3|4|5|6|7|8|9|one|two|three|four|five|six|seven|eight|nine))")
    var sum = 0
    for (line in lines) {

        // Find all matches for the above regex
        val matches = regex.findAll(line)

        // This took so long to figure out...
        var firstDigit = matches.first().destructured.component1()
        var lastDigit = matches.last().destructured.component1()

        // convert if needed from, for example, a "one" -> "1"
        if (firstDigit.length > 1) {
            firstDigit = part2Helper(firstDigit)
        }

        if (lastDigit.length > 1) {
            lastDigit = part2Helper(lastDigit)
        }

        val value = "$firstDigit$lastDigit".toInt()

        sum += value
    }

    println("Part 2 Result = $sum")
}

fun part2Helper(line:String) : String {
    if (line.contains("one")) return "1"
    if (line.contains("two")) return "2"
    if (line.contains("three")) return "3"
    if (line.contains("four")) return "4"
    if (line.contains("five")) return "5"
    if (line.contains("six")) return "6"
    if (line.contains("seven")) return "7"
    if (line.contains("eight")) return "8"
    if (line.contains("nine")) return "9"
    return "-999"
}