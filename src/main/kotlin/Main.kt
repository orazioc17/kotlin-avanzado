import java.lang.Exception
import java.util.*

typealias competidor = Person.Athlete
typealias aliasDato = MutableMap<Int, ArrayList<String>>

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

    println()
    println()
    println()
    println("#### Uso de typealias ####")
    usoTypealias()

    println()
    println()
    println()
    println("#### Uso de Desestructuracion ####")
    usoDesestructuracion()

    println()
    println()
    println()
    println("#### Uso de Try, Catch y Finally ####")
    usoTryCatchFinally()

    println()
    println()
    println()
    println("#### Uso de Throw ####")
    usoThrowExceptions()

    println()
    println()
    println()
    println("#### Uso de Scope Functions ####")
    usoScopeFunctions()

    println()
    println()
    println()
    println("#### Uso de Elvis Operator ####")
    usoElvisOperator()
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

    val array = IntArray(10) {5}
    println(array.show())

    val array2 = IntArray(10) {it} // it es el iterador, por lo que este array se llenara con los indices en
    // cada posicion del array
    println(array2.show())

    val array3 = IntArray(10) {it * 2} // Y aqui se llenara con los indices multiplicados por 2
    println(array3.show())

    // Renombrando el parametro por defecto que tiene la lambda (it)
    val array4 = IntArray(10) { i -> i * 3 }
    println(array4.show())

    // Manipulando variables externas a la lambda
    var suma = 0
    recorrerArray(array4) {
        suma += it
    }
    println("La suma de todos los elementos del array4 es: $suma") // 135

}

fun recorrerArray(array: IntArray, fn: (Int) -> Unit) {
    for (i in array) {
        fn(i)
    }
}

/***
 * Los typealias son para poder escribir menos codigo
 */
fun usoTypealias(){
    val deportista = competidor("Baseball")
    println("Cree un Person.Athlete")
    deportista.compete()

    val saludos:  aliasDato = mutableMapOf()
    saludos[0] = arrayListOf("Hola", "Adios")
    saludos[1] = arrayListOf("Hi", "Bye")
    println(saludos)
}

fun usoDesestructuracion() {
    val sol = Star("Sol", 1234756f, "Via Lactea")
    println(sol)
    val (name_star2, radius_star2, galaxy2) = Star("Sol2", 2178428f, "Via Lactea2")
    println("Datos star2 desestructurada: $name_star2 $radius_star2 $galaxy2")

    val (name_star3, radius_star3, ) = Star("Sol3", 21428f, "Via Lactea3")
    println("Datos star3 desestructurada: $name_star3 $radius_star3")

    // Para saltarseuna variable y no guardar esa informacion se usa guion bajo _
    val (name_star4, _, galaxy4) = Star("Sol4", 1848f, "Via Lactea4")
    println("Datos star4 desestructurada: $name_star4 $galaxy4")

    val componente = Star("Sol5", 8145848f, "Via Lactea5")
    println("Datos star5 con componentes: ${componente.component1()} ${componente.component2()} ${componente.component3()}")

    val saludos:  aliasDato = mutableMapOf()
    saludos[0] = arrayListOf("Hola", "Adios")
    saludos[1] = arrayListOf("Hi", "Bye")

    for ((index, palabras) in saludos) {
        println("$index $palabras")
    }

}

fun usoTryCatchFinally() {
    try {
        println("Division entre cero: ${5 / 0}")
    } catch (e: Exception) {
        println("Vamos a manejar este error")
        println("Tipo de error: $e")
    } finally {
        println("Pase lo que pase se ejecutara esto")
    }

    println()
    println("Guardando el try cath en una variable que a su vez es lambda")
    // Guardando el resultado de un try catch en una variable, al mismo tiempo haciendo uso de lambda
    val resultado: (Int, Int) -> Any = { a: Int, b: Int ->
        val res = try {
            println("$a entre $b: ${a / b}")
            a / b
        } catch (e: Exception) {
            println("Vamos a manejar este error")
            "Division no permitida"
        }
        res
    }

    val res1 = resultado(2,5)
    println(res1)
    val res2 = resultado(8,4)
    println(res2)
    val res3 = resultado(9,0)
    println(res3)

}

fun usoThrowExceptions() {
    val password: String = "1234567"
    if (password.length < 6) throw IllegalPasswordException("ShortPassword") // Exception("Password muy corta")
    else println("Password segura")
}

fun usoScopeFunctions() {
    val person = Person("Anyone", 1.56f, "V713918223892")

    // Para evitar estar llamando al objeto muchas veces se puede hacer de la siguiente forma
    person.let {
        // Cuando se usa let, se usa la palabra reservada it
        it.height = 1.6f
        it.passport = "V17841"
        it.die()
    }

    person.apply {
        // Mientras que con apply se usa this, aunque no hay necesidad de usarla
        this.height = 1.5f
        passport = "f891845"
        die()
    }

    val person2 = Person("Jaimito", 1.7f, "d282093").apply {
        //De todas formas es una mejor practica usar this para que el codigo sea mas legible
        this.height
        this.die()
    }

    val number = Person().run {
        // run lo que hace es ejecutar todo el codigo pero al final la variable va a guardar (opcionalmente) lo ultimo que se retorne, como en este caso height
        this.height = 1.9f
        this.passport = "dsjf234234"
        this.height
    }
    println(number)

    val estatura = with(Person(height = 1.56f)) {
        // with tambien retorna lo ultimo, como run, pero tiene una sintaxis distinta
        val string: String = if (this.height > 1.67f) {
            "La persona tiene una estatura promedio"
        } else "La persona no es muy alta"
        string
    }
    println(estatura)
}

fun usoElvisOperator() {
    var pais: String? = "Rusia"
    // El operador Elvis es para manejar los null, su es null se ejecuta lo de la derecha, si no, lo de la izquierda
    pais = pais?.uppercase() ?: "DESCONOCIDO"
    println(pais)

    var ciudad: String? = null
    ciudad = ciudad?.uppercase() ?: "CIUDAD DESCONOCIDA"
    println(ciudad)
}