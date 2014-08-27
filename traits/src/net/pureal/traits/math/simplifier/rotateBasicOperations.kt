package net.pureal.traits.math.simplifier

import net.pureal.traits.math.*
import net.pureal.traits.math.operations.*

data class pnRealArray(val p: Array<Real>, val n: Array<Real>)
fun rotateBasicOperations(it: Real): Real {

    // Inner fun
    fun rotateInnerAdd(it: Real): Real {
        if (it !is AdditionValue && it !is SubtractionValue) return it
        fun pn_add(it: Real): pnRealArray {
            if (it.isZero) return pnRealArray(array(), array())
            if (it !is AdditionValue && it !is SubtractionValue)
                return if (it.isPositive) pnRealArray(array(rotateBasicOperations(it)), array())
                else pnRealArray(array(), array(rotateBasicOperations(-it)))
            val r1 = pn_add(it.subReals[0])
            val r2 = pn_add(it.subReals[1])
            if (it is AdditionValue) return pnRealArray((r1.p + r2.p).copyToArray(), (r1.n + r2.n).copyToArray())
            return pnRealArray((r1.p + r2.n).copyToArray(), (r1.n + r2.p).copyToArray())
        }
        val pn = pn_add(it)
        val p = pn.p
        val n = pn.n
        var pos: Real = if (!p.isEmpty()) p[0] else real(0)
        var i: Int = 1
        while (p.size > i) {
            pos = pos + p[i]
            i++
        }
        if (n.isEmpty()) return pos
        var neg: Real = n[0]
        i = 1
        while (n.size > i) {
            neg = neg + n[i]
            i++
        }
        return pos - neg
    }
    // Inner Fun
    fun rotateInnerMul(it: Real): Real {
        return it
    }

    // Code starts here
    when (it) {
        is RealBinaryOperation -> {
            if (it is AdditionValue || it is SubtractionValue) return rotateInnerAdd(it)
            if (it is MultiplicationValue || it is DivisionValue) return rotateInnerMul(it)
            return it
        }
        else -> return it
    }
}

fun Real.rotate(): Real = rotateBasicOperations(this)