package net.pureal.traits.graphics

public trait Vector2 {
    fun plus(other : Vector2) : Vector2
    fun times(other : Number) : Vector2

    fun plus() = this
    fun minus() = (net.pureal.traits.graphics.Vector2) * -1
    fun minus(other : net.pureal.traits.graphics.Vector2) = this + (-net.pureal.traits.graphics.Vector2.minus.other)
    fun div(other : Number) = this net.pureal.traits.graphics.Vector2.times (1/other.toDouble())

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