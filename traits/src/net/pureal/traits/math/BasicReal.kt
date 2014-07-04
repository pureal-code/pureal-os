package net.pureal.traits.math

public trait BasicReal : Number {
    override fun toDouble() : Double {
        return 0.0
    }
    override fun toFloat() : Float {
        return toDouble().toFloat()
    }
    override fun toLong() : Long {
        return 0
    }
    override fun toInt() : Int {
        return toLong().toInt()
    }
    override fun toShort() : Short {
        return toLong().toShort()
    }
    override fun toByte() : Byte {
        return toLong().toByte()
    }
    override fun toChar() : Char {
        return toLong().toChar()
    }

    // This is real magic
    val number : BasicInt
    val exponent : BasicInt

    fun minus() : BasicReal = basicReal(-number, exponent)

    fun isNaN() : Boolean = false

}



fun basicReal(s : String) : BasicReal = basicReal(basicInt(0), basicInt(0))

fun basicReal(num : BasicInt, exp : BasicInt) : BasicReal = object : BasicReal {
    override val number : BasicInt = num
    override val exponent : BasicInt = exp
}