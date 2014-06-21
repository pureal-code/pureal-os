package net.pureal.traits

trait Vector3 {
    fun plus(other : Vector3) = vectorOf(x.toDouble() + other.x.toDouble(), y.toDouble() + other.y.toDouble(), z.toDouble() + other.z.toDouble())
    fun times(other : Number) : Vector3 {
        val s = other.toDouble()
        return vectorOf(x.toDouble() * s, y.toDouble() * s, z.toDouble() * s)
    }

    fun plus() = this
    fun minus() = this * -1
    fun minus(other : Vector3) = this + (-other)
    fun div(other : Number) = this * (1/other.toDouble())
    fun get(i : Int) : Number = when(i) {
        0 -> x
        1 -> y
        2 -> z
        else -> {throw IllegalArgumentException()}
    }
    val x : Number
    val y : Number
    val z : Number

    val lengthSquared : Number get() {
        val x = x.toDouble()
        val y = y.toDouble()
        val z = z.toDouble()
        return x*x + y*y + z*z
    }
    val length : Number get() = Math.sqrt(lengthSquared.toDouble())

    fun times(other : Vector3) : Number = x.toDouble() * other.x.toDouble() + y.toDouble() * other.y.toDouble() + z.toDouble() * other.z.toDouble()
}

fun vectorOf(x : Number, y : Number, z : Number) = object : Vector3 {
    override val x = x
    override val y = y
    override val z = z
}

fun vector3Of(get : (Int) -> Number) = vectorOf(get(0), get(1), get(2))

val zeroVector3 = vectorOf(0,0,0)