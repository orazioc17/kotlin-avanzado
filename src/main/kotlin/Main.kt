import java.util.*

fun main(args: Array<String>) {
    println("#### Uso de funciones de extencion ####")
    usoDeFuncionesExtension()

    println()
    println()
    println()
    println("#### Uso de High Order Functions ####")
    usoHighOrderFunctions()

    println()
    println()
    println()
    println("#### Uso de High Order Functions en objetos ####")
    usoHighOrderFunctionsEnObjetos()

    println()
    println()
    println()
    println("#### Uso de Lambdas ####")
    usoLambdas()
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

fun usoHighOrderFunctionsEnObjetos() {
    val orazio = Person("Orazio", 1.77f)
    val mario = Person("Mario", 1.63f)

    println("${orazio.name} policia en Italia? ${if (orazio.checkPolice(::policeItalia)) "Si" else "No"}")
    println("${orazio.name} policia en Venezuela? ${if (orazio.checkPolice(::policeVenezuela)) "Si" else "No"}")
    println("${mario.name} policia en Italia? ${if (mario.checkPolice(::policeItalia)) "Si" else "No"}")
    println("${mario.name} policia en Venezuela? ${if (mario.checkPolice(::policeVenezuela)) "Si" else "No"}")
}

fun policeItalia(h: Float) = h >= 1.7f

fun policeVenezuela(h: Float) = h >= 1.6f

/*
* A continuacion se esta haciendo una funcion de extension de una clase que nosotros mismos creamos, como tal esto
* se puede hacer, pero no es para nada recomendado ya que si nosotros mismos creamos la clase, es mejor y una buena
* practica que nosotros mismos creemos la funcion dentro de la clase, haciendo buen uso del encapsulamiento, las
* funciones de extension solo se deberian usar en objetos que ya vengan por defecto en kotlin, como los String
* */
fun Person.checkPolice(fn: (Float) -> Boolean) = fn(height)

fun usoLambdas() {
    val n1 = 12
    val n2 = 3
    var funcionLambda = { x: Int, y: Int -> x + y}

    // Aqui es el uso de lambda como high order function, pues la funcion es variable como se vera a continuacion
    // Aunque primero se usa una variable con la funcion
    println("La suma de $n1 y $n2 es: ${calculadora(n1, n2, funcionLambda)}")

    funcionLambda = { x: Int, y: Int -> x - y}
    println("La resta de $n1 y $n2 es: ${calculadora(n1, n2, funcionLambda)}")

    // Ahora si se implementa con lambda (funcion anonima)
    println("La multiplicacion de $n1 y $n2 con lambda es: ${calculadora(n1, n2, { x: Int, y: Int -> x * y })}")

    // Como se puede ver, las lambdas se pueden enviar por fuera del parentesis, es mas, el IDE lo recomienda, es para
    // mejor comprension del codigo
    println("La potencia de 2 elevado a 5 con lambda es: ${calculadora(n1, n2)
    // Lo importante es que la ultima linea tenga un valor (sin return)
    { x, y ->
        var valor = 1
        for (i in 1..y) valor *= x
        valor
    }
    }")
}