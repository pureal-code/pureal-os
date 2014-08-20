package net.pureal.traits.math.simplifier

import net.pureal.traits.math.*
import net.pureal.traits.math.operations.*

data class pnRealArray(val p: Array<Real>, val n: Array<Real>)
fun rotateBasicOperations(it: Real): Real {


    fun rotateInnerAdd(it: Real): Real {
        if(it !is AdditionValue && it !is SubtractionValue) return it
        fun pn_add(it: Real): pnRealArray {
            if(it !is AdditionValue && it !is SubtractionValue) return pnRealArray(array(it), array())
            val r1 = pn_add(it.subReals[0])
            val r2 = pn_add(it.subReals[1])
            if(it is AdditionValue) return pnRealArray((r1.p + r2.p).copyToArray(), (r1.n + r2.n).copyToArray())
            return pnRealArray((r1.p + r2.n).copyToArray(), (r1.n + r2.p).copyToArray())
        }
        val pn = pn_add(it)
        val p = pn.p.filter { it. }
        val n = pn.n
        var pos: Real = p[0]
    }
    fun rotateInnerMul(it: Real): Real {
        return it
    }
    when(it) {
        is RealBinaryOperation -> {
            if(it is AdditionValue || it is SubtractionValue) return rotateInnerAdd(it)
            if(it is MultiplicationValue || it is DivisionValue) return rotateInnerMul(it)
        }
        //TODO
        else -> {}
    }
    return it
}