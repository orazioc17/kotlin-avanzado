import java.util.*

fun main(args: Array<String>) {
    println("#### Uso de funciones de extencion ####")
    usoDeFuncionesExtension()

    println()
    println()
    println()
    println("#### Uso de High Order Functions ####")
    usoHighOrderFunctions()
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

fun usoHighOrderFunctions() {
    val n1 = 12
    val n2 = 3
    // Para pasar las funciones como parametros se les debe poner 2 :: antes del nombre de la funcion
    println("La suma de $n1 y $n2 es: ${calculadora(n1, n2, ::suma)}")
    println("La resta de $n1 y $n2 es: ${calculadora(n1, n2, ::resta)}")
    println("La multiplicacion de $n1 y $n2 es: ${calculadora(n1, n2, ::multiplicacion)}")
    println("La division de $n1 y $n2 es: ${calculadora(n1, n2, ::division)}")
}

fun calculadora(n1: Int, n2: Int, fn: (Int, Int) -> Int): Int {
    return fn(n1, n2)
}

fun suma (x: Int, y: Int) = x + y

fun resta (x: Int, y: Int) = x - y

fun multiplicacion (x: Int, y: Int) = x * y

fun division (x: Int, y: Int) = x / y