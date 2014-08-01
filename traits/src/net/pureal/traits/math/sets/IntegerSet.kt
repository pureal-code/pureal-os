package net.pureal.traits.math.sets

import net.pureal.traits.math.*
import net.pureal.traits.*

public trait IntegerSet : RealSet {
    public class object : IntegerSet, Constructor4<IntegerSet, Number, Number, Boolean, Boolean>, Constructor2<IntegerSet, Number, Number> {
        override val lowEnd: Calculatable = -Infinity
        override val highEnd: Calculatable = Infinity
        override val lowClosed: Boolean = false
        override val highClosed: Boolean = false

        public val Full: IntegerSet = IntegerSet
        public val PositiveAndZero: RealSet = invoke(0,Infinity,true,false)
        public val Positive: RealSet = invoke(0,Infinity,false,false)
        public val NegativeAndZero: RealSet = invoke(-Infinity,0,false,true)
        public val Negative: RealSet = invoke(-Infinity,0,false,false)

        override fun invoke(le: Number, he: Number) = invoke(le, he, true, true)
        override fun invoke(lEnd: Number, hEnd: Number, lClosed: Boolean, hClosed: Boolean): IntegerSet {
            var le = lEnd.asCalculatable()
            var he = hEnd.asCalculatable()
            if(le > he) {
                val tmp = le
                le = he
                he = tmp
            }
            assert((-Infinity != le || !lClosed) && (Infinity != he || !hClosed), "A Set cannot be closed in the infinite")
            return object: IntegerSet {
                override val lowEnd: Calculatable = le
                override val highEnd: Calculatable = he
                override val lowClosed: Boolean = lClosed
                override val highClosed: Boolean = hClosed
            }
        }
    }

    override fun contains(other: Set): Boolean {
        // TODO
        return false
    }
    override fun contains(other: Number): Boolean {
        return super.contains(other) && when (other) {
            is Long -> true
            is Int -> true
            is Short -> true
            is Byte -> true
            is InternalReal -> other.isInteger()
            is Double -> other % 1.0 == 0.0
            is Float -> other % 1.0f == 0.0f
            else -> false
        }
    }

    override fun hasCommonElementsWith(other: Set): Boolean {
        // TODO
        return false
    }
}

val integerSet = IntegerSet
fun openIntegerSet(lEnd: Number, hEnd: Number) = integerSet(lEnd, hEnd, false, false)