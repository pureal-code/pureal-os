package net.pureal.traits

public trait Vector2 {
    fun plus(other : Vector2) : Vector2
    fun times(other : Number) : Vector2

    fun plus() = this
    fun minus() = this * -1
    fun minus(other : Vector2) = this + (-other)
    fun div(other : Number) = this * (1/other.toDouble())

    val x : Number
    val y : Number

    val lengthSquared : Number
        get() {
            val x = x.toDouble()
            val y = y.toDouble()
            return x*x + y*y
        }
    val length : Number get() = Math.sqrt(lengthSquared.toDouble())
    val argument : Number get() = Math.atan2(y.toDouble(), x.toDouble())
}