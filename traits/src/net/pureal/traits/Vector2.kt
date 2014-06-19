package net.pureal.traits

trait Vector2 {
    fun plus(other : Vector2) = vectorOf(x.toDouble() + other.x.toDouble(), y.toDouble() + other.y.toDouble())
    fun times(other : Number) : Vector2 {
        val s = other.toDouble()
        return vectorOf(x.toDouble() * s, y.toDouble() * s)
    }

    fun plus() = this
    fun minus() = this * -1
    fun minus(other : Vector2) = this + (-other)
    fun div(other : Number) = this * (1/other.toDouble())

    val x : Number
    val y : Number

    val lengthSquared : Number get() {
        val x = x.toDouble()
        val y = y.toDouble()
        return x*x + y*y
    }
    val length : Number get() = Math.sqrt(lengthSquared.toDouble())
    val argument : Number get() = Math.atan2(y.toDouble(), x.toDouble())
}

fun vectorOf(x : Number, y : Number) = object : Vector2 {
    override val x = x
    override val y = y
}