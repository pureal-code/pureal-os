package net.pureal.traits.math


public trait RealPrimitive : Real {
    val value: Number

    override val isPrimitive : Boolean get() = true

    override fun toString() = "real(\""+value.toString()+"\")"

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

fun real(v:Number) = object : RealPrimitive {
    override val value : Number = v.toDouble()
}

fun real(s:String) = object : RealPrimitive {
    override val value = basicReal(s)
}

fun Number.toReal() : RealPrimitive = real(this)

