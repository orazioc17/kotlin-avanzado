class Person(var name: String = "Sin Nombre", var height: Float = 1.6f) {

    fun Person() {
        name = "Juan"
        height = 1.6f
    }

    class Athlete(var sport: String = "Soccer") {

        fun compete() {
            println("I'm playing $sport")
        }
    }

    // fun checkPolice(fn: (Float) -> Boolean) = fn(height)
}