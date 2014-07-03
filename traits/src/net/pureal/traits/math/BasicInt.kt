package net.pureal.traits.math

public trait BasicInt : Number {
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

    val value : IntArray

    fun toBasicReal() : BasicReal = basicReal(this, basicInt(0))

    fun minus() : BasicInt = this // TODO: do this when I got some clue
}

// It must still be evaluated if the signed Int makes problems and if wae can use an unsigned Int or if we need w/e
fun basicInt(i : Int) : BasicInt {
    var arr: IntArray = IntArray(0)
    arr[0] = i
    return object : BasicInt {
        override val value: IntArray = arr
    }
}