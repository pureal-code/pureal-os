package net.pureal.traits.math

public trait BasicReal : BasicInt {
    override fun toDouble() : Double {
        return 0.0
    }
    override fun toLong() : Long {
        return 0
    }

    // This is real magic
    val exponent : BasicInt

    override fun minus() : BasicReal = basicReal(basicInt(number, !sign), exponent)

    override fun compareTo(other: BasicInt) : Int {
        return 0
    }

    fun plusBI(other : BasicInt){

    }

    fun minusBI(other : BasicInt){

    }

    fun timesBI(other : BasicInt){

    }

    fun divBI(other : BasicInt){

    }

    override fun equals(other : Any?) : Boolean
    {
        when (other) {
            null -> return false
            is Number -> return compareTo(other)==0
            else -> return false
        }
    }


    override fun isInteger() : Boolean = exponent >= 0
}



fun basicReal(s : String) : BasicReal = basicReal(basicInt(0), basicInt(0))

fun basicReal(num : BasicInt, exp : BasicInt = basicInt(0)) : BasicReal = object : BasicReal {
    override val number : IntArray = num.number
    override val sign : Boolean = num.sign
    override val exponent : BasicInt = exp
}

fun basicReal(d : Double) : BasicReal = basicReal(d.toString())

fun basicReal(f : Float) : BasicReal = basicReal(f.toString())

fun basicReal(l : Long) : BasicReal = basicReal(basicInt(l))

fun basicReal(i : Int) : BasicReal = basicReal(basicInt(i))

fun basicReal(s : Short) : BasicReal = basicReal(basicInt(s))

fun basicReal(b : Byte) : BasicReal = basicReal(basicInt(b))
