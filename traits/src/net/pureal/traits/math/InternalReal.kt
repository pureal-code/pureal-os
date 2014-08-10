package net.pureal.traits.math

public trait InternalReal : Calculatable {

    override fun toDouble(): Double
    override fun toFloat(): Float = toDouble().toFloat()
    override fun toLong(): Long
    override fun toInt(): Int = toLong().toInt()
    override fun toShort(): Short = toLong().toShort()
    override fun toByte(): Byte = toLong().toByte()
    override fun toChar(): Char = toLong().toChar()

    override fun toString(): String

    fun toMathematicalString(): String // TODO: i would like the environment to do the conversion, using a xxxRealConverter trait

    override fun equals(other: Any?): Boolean {
        try {
            return compareTo(other) == 0
        } catch (e: IllegalArgumentException) {
            return false
        }
    }

    override fun plus(other: Any?): InternalReal
    override fun minus(other: Any?): InternalReal
    override fun times(other: Any?): InternalReal
    override fun div(other: Any?): InternalReal
    override fun mod(other: Any?): InternalReal
    /// throws RuntimeException for other == 0

    override fun minus(): InternalReal
    override fun plus() = this


    override fun floor(): InternalReal
    override fun ceil(): InternalReal
    override fun round(): InternalReal
    override fun abs(): InternalReal

    fun signum(): Int

    val sign: Boolean get() = signum() < 0

    fun isInteger(): Boolean

}