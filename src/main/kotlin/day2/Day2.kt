package day2

import common.getLines

class Game(rawGameData: String) {
    var gameId = 0
        private set

    var redMinimum = 0
        private set

    var greenMinimum = 0
        private set

    var blueMinimum = 0
        private set

    private var rounds = ArrayList<Round>()

    init {
        parseRawGameDataAndConstruct(rawGameData)
    }

    fun isGamePossible(numRed:Int, numGreen:Int, numBlue:Int): Boolean {
        rounds.forEach { round ->
            if (round.numRed > numRed) return false
            if (round.numGreen > numGreen) return false
            if (round.numBlue > numBlue) return false
        }
        return true
    }

    fun setColorMinimums() {
        rounds.forEach { round ->
            if (round.numRed > redMinimum) redMinimum = round.numRed;
            if (round.numGreen > greenMinimum) greenMinimum = round.numGreen;
            if (round.numBlue > blueMinimum) blueMinimum = round.numBlue;
        }
    }

    fun printRounds() {
        rounds.forEach { round ->
            println("Number of red = ${round.numRed}, Number of green = ${round.numGreen}, Number of blue = ${round.numBlue}")
        }
    }

    private fun parseRawGameDataAndConstruct(rawGameData: String) {
        val firstSplit = rawGameData.split(":")
        gameId = firstSplit[0].split(" ")[1].toInt();

        val secondSplit = firstSplit[1].split(";")

        secondSplit.forEach {rawRound ->
            val round = Round(rawRound)
            rounds.add(round)
        }
    }
}

class Round(rawRoundData: String) {
    var numRed = 0
    private set
    var numGreen = 0
    private set
    var numBlue = 0
    private set

    init {
        parseRawRoundDataAndConstruct(rawRoundData)
    }

    private fun parseRawRoundDataAndConstruct(rawRoundData: String) {
        val split = rawRoundData.split(",")
        split.forEach { s ->
            val finalRawLine = s.substring(1)
            val sep = finalRawLine.split(" ")
            if (sep[1] == "red") {
                numRed += sep[0].toInt()
            } else if (sep[1] == "green") {
                numGreen += sep[0].toInt()
            } else if (sep[1] == "blue") {
                numBlue += sep[0].toInt()
            }
        }
    }
}

fun main() {
    part1()
    part2()
}

fun part1() {
    val lines = getLines("src/main/resources/day2/day2.txt")
    var count = 0;
    for (line in lines) {
        val game = Game(line)
        if (game.isGamePossible(12, 13, 14)) count += game.gameId;
    }

    println("Part 1 = $count")
}

fun part2() {
    val lines = getLines("src/main/resources/day2/day2.txt")
    var count = 0
    for (line in lines) {
        val game = Game(line)
        game.setColorMinimums();
        println("${game.redMinimum}, ${game.greenMinimum}, ${game.blueMinimum}");
        count += (game.redMinimum * game.greenMinimum * game.blueMinimum)
    }

    println("Part 2 = $count")
}

