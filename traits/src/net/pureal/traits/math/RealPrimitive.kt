package net.pureal.traits.math


public trait RealPrimitive : Real {
    val value: Number

    override val isPrimitive : Boolean
        get() = true

    override fun toString() : String = value.toString()

    fun getValue() : Number = value
}

fun real(v:Number) : RealPrimitive =  object : RealPrimitive {
    override val value : Number = v
}

fun Number.toReal() : RealPrimitive = real(this)