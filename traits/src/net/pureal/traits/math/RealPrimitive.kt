package net.pureal.traits.math


public trait RealPrimitive : Real {
    val value: Number

    override val isPrimitive : Boolean get() = true

    override fun toString() = value.toString()

    override fun Number() = value
}

fun real(v:Number) = object : RealPrimitive {
    override val value : Number = v.toDouble()
}

fun Number.toReal() : RealPrimitive = real(this)