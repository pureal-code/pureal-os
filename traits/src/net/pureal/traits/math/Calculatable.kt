package net.pureal.traits.math

public abstract class Calculatable : Number(), Comparable<Any?> {
    // INHERITED STUFF
    override abstract fun compareTo(other: Any?): Int

    override abstract fun toDouble(): Double
    override fun toFloat(): Float = toDouble().toFloat()
    override abstract fun toLong(): Long
    override fun toInt(): Int = toLong().toInt()
    override fun toShort(): Short = toLong().toShort()
    override fun toByte(): Byte = toLong().toByte()
    override fun toChar(): Char = toLong().toChar()

    // WHAT IS IMPORTANT
    open fun plus(): Calculatable = this

    abstract fun minus(): Calculatable
    abstract fun plus(other: Any?): Calculatable
    abstract fun minus(other: Any?): Calculatable
    abstract fun times(other: Any?): Calculatable
    abstract fun div(other: Any?): Calculatable

    abstract fun floor(): Calculatable
    abstract fun ceil(): Calculatable
    abstract fun round(): Calculatable

    abstract fun abs(): Calculatable
}

fun Number.asCalculatable(): Calculatable = if(this is Calculatable) this; else ee.intReal(this)