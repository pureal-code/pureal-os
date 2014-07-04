package net.pureal.traits.math

import com.sun.javaws.exceptions.InvalidArgumentException


/** Basic Int:
 *  Subtype of BasicReal that is always Integer with exp 0
 *  to facilitate calculations ...
 */
public trait BasicInt : BasicReal {

    override val exponent : Long get() = 0

    fun toBasicReal() : BasicReal = basicReal(this, 0)

    override fun minus() : BasicInt = basicInt(number, !sign)



    override fun isInteger() : Boolean = true
}

// It must still be evaluated if the signed Int makes problems and if wae can use an unsigned Int or if we need w/e
fun basicInt(i : Int) : BasicInt = basicInt(i.toLong())

fun basicInt(s : Short) : BasicInt = basicInt(s.toLong())

fun basicInt(b : Byte) : BasicInt = basicInt(b.toLong())

fun basicInt(l : Long) : BasicInt {
    var n = l
    var arr: IntArray = IntArray(0)
    var sgn: Boolean = n < 0
    if(sgn) n = -n
    var index : Int = 0
    while(n > maxSimpleInt){
        val dig : Int = (n % basicDivisor).toInt()
        arr[index] = dig
        n = n / basicDivisor
        index++
    }
    arr[index] = n.toInt()

    return object : BasicInt {
        override val number : IntArray = arr
        override val sign : Boolean = sgn
    }
}

fun basicInt(s : String) : BasicInt {
    // TODO: control w/ regex if s only has numbers
    return basicInt(s.toLong())
}

fun basicInt(num : IntArray, sgn: Boolean = false) : BasicInt {
    // TODO: check if num has desired format
    return object : BasicInt {
        override val number = num
        override val sign = sgn
    }
}

