package net.pureal.traits.math

object NaN : BasicReal {
    override val number : BasicInt = basicInt(0)
    override val exponent : BasicInt = basicInt(0)
    override fun isNaN() : Boolean = true

    override fun equals(other:Any?) : Boolean {
        return other is BasicReal && other.isNaN()
    }
}