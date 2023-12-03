package common

import java.io.File

fun getLines(fileName: String): List<String>
        = File(fileName).readLines();