package net.pureal.traits.math.sets

import net.pureal.traits.math.*

public trait RealSet : Set {
    public class object : RealSet {
        override val lowEnd: Calculatable = -Infinity
        override val highEnd: Calculatable = Infinity
        override val lowClosed: Boolean = false
        override val highClosed: Boolean = false
        public val Full: RealSet = RealSet
        public val PositiveAndZero: RealSet = realSet(0,Infinity,true,false)
        public val Positive: RealSet = openRealSet(0,Infinity)
        public val NegativeAndZero: RealSet = realSet(-Infinity,0,false,true)
        public val Negative: RealSet = openRealSet(-Infinity,0)
    }
    val lowEnd: Calculatable
    val highEnd: Calculatable

    val lowClosed: Boolean
    val highClosed: Boolean

    override fun contains(other: Set): Boolean {
        when(other) {
            is SetUnion -> return contains(other.subset1) && contains(other.subset2)
            is SetIntersection -> {
                val u = other.combineSets()
                if(u is SetIntersection) return contains(other.superset1) || contains(other.superset2)
                return contains(u)
            }
            is RealSet -> {
                return (other.lowEnd in this || (!other.lowClosed && !lowClosed && lowEnd == other.lowEnd))
                    && (other.highEnd in this || (!other.highClosed && !highClosed && highEnd == other.highEnd))
            }
            else -> return false
        }
    }
    override fun contains(other: Number): Boolean {
        val o = other.asCalculatable()
        return (if(highClosed) o <= highEnd; else o < highEnd) && (if(lowClosed) o >= lowEnd; else o > lowEnd)
    }

    override fun hasCommonElementsWith(other: Set): Boolean {
        // TODO
        return false
    }

}

fun realSet(lEnd: Number, hEnd: Number, lClosed: Boolean = true, hClosed: Boolean = true): RealSet {
    var le = lEnd.asCalculatable()
    var he = hEnd.asCalculatable()
    if(le > he) {
        val tmp = le
        le = he
        he = tmp
    }
    assert((-Infinity != le || !lClosed) && (Infinity != he || !hClosed), "A Set cannot be closed in the infinite")
    return object: RealSet {
        override val lowEnd: Calculatable = le
        override val highEnd: Calculatable = he
        override val lowClosed: Boolean = lClosed
        override val highClosed: Boolean = hClosed
    }
}

fun openRealSet(lEnd: Number, hEnd: Number) = realSet(lEnd, hEnd, false, false)