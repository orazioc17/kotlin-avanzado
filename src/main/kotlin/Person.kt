class Person(var name: String = "Sin Nombre", var height: Float = 1.6f, var passport: String = "#00000000") {

    var alive = true

    fun Person() {
        name = "Juan"
        height = 1.6f
    }

    fun die() {
        alive = false
    }

    class Athlete(var sport: String = "Soccer") {

        fun compete() {
            println("I'm playing $sport")
        }
    }

    // fun checkPolice(fn: (Float) -> Boolean) = fn(height)
}