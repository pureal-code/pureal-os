package net.pureal.traits.math

public trait InternalReal : Number, Comparable<Any?> {

    override fun toDouble(): Double
    override fun toFloat(): Float = toDouble().toFloat()
    override fun toLong(): Long
    override fun toInt(): Int = toLong().toInt()
    override fun toShort(): Short = toLong().toShort()
    override fun toByte(): Byte = toLong().toByte()
    override fun toChar(): Char = toLong().toChar()

    override fun toString(): String

    fun toMathematicalString(): String // NOTE: i would like the environment to do the conversion, using a xxxRealConverter trait

    override fun compareTo(other: Any?): Int


    override fun equals(other: Any?): Boolean {
        try {
            return compareTo(other) == 0
        } catch (e: IllegalArgumentException) {
            return false
        }
    }

    fun plus(other: Any?): InternalReal
    fun minus(other: Any?): InternalReal
    fun times(other: Any?): InternalReal
    fun div(other: Any?): InternalReal
    /// throws RuntimeException for

    fun minus(): InternalReal

    fun signum(): Int

    val sign: Boolean get() = signum() < 0

    fun isInteger(): Boolean

}