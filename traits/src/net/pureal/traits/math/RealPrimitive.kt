package net.pureal.traits.math


public trait RealPrimitive : Real {
    val value: Number

    override val isPrimitive : Boolean
        get() = true

    override fun toString() : String = value.toString()

    override fun Number() : Number = value
}

fun real(v:Number) = object : RealPrimitive {

    override val value : Number = v.toDouble()
}

fun Number.toReal() : RealPrimitive = real(this)