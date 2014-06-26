package net.pureal.traits.math

public trait MathOperator {
    val description : String // description of the operator

    val priority : Number  // A priority value of the operator, so that the runtime knows when to set braces

    fun getStringRepresentation(operands : Array<Number>, outerPriority : Number) : String

    fun executeOn(operands : Array<Number>) : MathValue

}