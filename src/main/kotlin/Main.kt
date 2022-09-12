import java.util.*

fun main(args: Array<String>) {
    usoDeFuncionesExtension()
}

fun usoDeFuncionesExtension() {
    val string = "esta es una funcion de prueba"
    println(string.trimAndCapitalize())
    println(string.charCount())

    val array = intArrayOf(1,2,3,4,5)
    println(array)
    println(array.show())

}

fun String.trimAndCapitalize(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }.replace(
        " ", "")
}

fun String.charCount(): Int {
    return this.replace(" ", "").length
}

fun IntArray.show(): String {
    var string = "["
    for (i in this) string += "$i, "
    string += "]"
    return string.replace(", ]", "]")
}
