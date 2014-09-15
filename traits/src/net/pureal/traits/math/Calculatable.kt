package net.pureal.traits.math

public abstract class Calculatable : Number(), Comparable<Any?> {
    // INHERITED STUFF

    final override fun compareTo(other: Any?): Int {
        return compareTo(other, false)
    }

    final fun compareTo(other: Any?, inner: Boolean): Int {
        if (other == null) throw IllegalArgumentException()
        val it = other.asCalculatable()
        try {
            return tryCompareTo(it)
        } catch (e: IllegalArgumentException) {
            try {
                return -it.tryCompareTo(this)
            } catch (e: IllegalArgumentException) {
                if (!inner) return activeEnvironment.intReal(this).compareTo(activeEnvironment.intReal(other), true)
                throw e
            }
        }
    }

    override fun equals(other: Any?): Boolean = compareTo(other) == 0

    override abstract fun toDouble(): Double
    override fun toFloat(): Float = toDouble().toFloat()
    override abstract fun toLong(): Long
    override fun toInt(): Int = toLong().toInt()
    override fun toShort(): Short = toLong().toShort()
    override fun toByte(): Byte = toLong().toByte()
    override fun toChar(): Char = toLong().toChar()

    // WHAT IS IMPORTANT
    abstract fun tryCompareTo(other: Calculatable): Int

    open fun plus(): Calculatable = this

    abstract fun minus(): Calculatable
    abstract fun plus(other: Any?): Calculatable
    abstract fun minus(other: Any?): Calculatable
    abstract fun times(other: Any?): Calculatable
    abstract fun div(other: Any?): Calculatable
    abstract fun mod(other: Any?): Calculatable

    abstract fun floor(): Calculatable
    abstract fun ceil(): Calculatable
    abstract fun round(): Calculatable

    abstract fun abs(): Calculatable

    final val shell: MathShell
    {
        shell = activeShell
    }
    final val env: MathEnvironment get() = shell.environment
}

fun Any.asCalculatable(): Calculatable = if (this is Calculatable) this; else activeEnvironment.intReal(this)

fun gcd(a: Number, b: Number): Calculatable {
    var a = a.asCalculatable().abs()
    var b = b.asCalculatable().abs()
    var c: Calculatable
    if (a equals 0) return b
    if (b equals 0) return a
    if (a > b) {
        c = a
        a = b
        b = c
    }
    var i = 0
    while (a > 0 && a != b && i < 2 * activeEnvironment.accuracy) {
        c = b % a
        b = a
        a = c
        i++
    }
    if (i >= 2 * activeEnvironment.accuracy) return 0.asCalculatable()
    return b
}

fun lcm(a: Number, b: Number): Calculatable {
    val a = a.asCalculatable().abs()
    val b = b.asCalculatable().abs()
    val g = gcd(a, b)
    if (g equals 0) return Infinity
    return a * b / g
}