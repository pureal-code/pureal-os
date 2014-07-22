package net.pureal.traits.math.sets

import net.pureal.traits.math.*

public trait RealSet : Set {
    public class object {
        public val Full: RealSet = realSet(-Infinity,Infinity,false,false)
    }
    val lowEnd: Calculatable
    val highEnd: Calculatable

    val lowClosed: Boolean
    val highClosed: Boolean

    override fun contains(other: Set): Boolean {
        when(other) {
            is SetUnion -> return contains(other.subset1) && contains(other.subset2)
            is SetIntersection -> return contains(other.superset1) && contains(other.superset2)
            else -> return false
        }
    }
    override fun contains(other: Number): Boolean {
        val o = other.asCalculatable()
        return (if(highClosed) o <= highEnd; else o < highEnd) && (if(lowClosed) o >= lowEnd; else o > lowEnd)
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