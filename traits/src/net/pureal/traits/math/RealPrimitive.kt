package net.pureal.traits.math


public trait RealPrimitive : Real {
    val value: Number

    override val isPrimitive : Boolean get() = true

    // TODO: replace with BasicReal later
    override fun toString() = value.toString()

    override fun Number() = value

    override fun equals(other : Any?) : Boolean {
        return when(other){
            is RealPrimitive -> value == other.value
            is Number -> value == other
            else -> false
        }
    }


    override fun minus() : Real {
        return real(-(value.toDouble()))
    }
}

fun real(v:Number) : RealPrimitive {
    if (v is Real) throw IllegalArgumentException("A RealPrimitive cannot be created with an expression")
    return object : RealPrimitive {
        override val value : Number = v.toDouble()
    }
}

fun Number.toReal() : RealPrimitive = real(this)

