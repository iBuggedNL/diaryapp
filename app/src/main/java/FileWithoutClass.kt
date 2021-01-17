fun aMethodOutsideOfAClass(): String {
    return "Wow this is so awesome I do not need to define throwaway classes for utility functions anymore"
}

fun anotherExpressionMethod() = "I don't even need to specify return type or a body"

const val CONSTANT = "The same goes for constants"

val otherValue = "Or other values"

// extension function - welcome to the crazy zone
fun Int.divideByTwo() = this / 2