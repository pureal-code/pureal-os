package net.pureal.traits.math.sets

import net.pureal.traits.math.*
import net.pureal.traits.Constructor2

public trait SetIntersection : net.pureal.traits.math.Set {
    companion object : Constructor2<SetIntersection, net.pureal.traits.math.Set, net.pureal.traits.math.Set> {
        override fun invoke(ss1: net.pureal.traits.math.Set, ss2: net.pureal.traits.math.Set): SetIntersection = object : SetIntersection {
            override val superset1: net.pureal.traits.math.Set = ss1
            override val superset2: net.pureal.traits.math.Set = ss2
        }
    }

    val superset1: net.pureal.traits.math.Set
    val superset2: net.pureal.traits.math.Set

    override fun toString(): String = "setIntersection(${superset1},${superset2})"

    fun simplifySets(): net.pureal.traits.math.Set {
        var s_ss1: net.pureal.traits.math.Set = superset1
        var s_ss2: net.pureal.traits.math.Set = superset2
        when (s_ss1) {
            is SetIntersection -> s_ss1 = (s_ss1 as SetIntersection).simplifySets()
            is SetUnion -> s_ss1 = (s_ss1 as SetIntersection).simplifySets()
            is EmptySet -> return EmptySet
        }
        when (s_ss2) {
            is SetIntersection -> s_ss2 = (s_ss2 as SetIntersection).simplifySets()
            is SetUnion -> s_ss2 = (s_ss1 as SetIntersection).simplifySets()
            is EmptySet -> return EmptySet
        }
        if (s_ss1 !is RealSet || s_ss2 !is RealSet) return setIntersection(s_ss1, s_ss2)
        val t_ss1: RealSet = s_ss1 as RealSet
        val t_ss2: RealSet = s_ss2 as RealSet

        val le: Calculatable
        val he: Calculatable
        val lc: Boolean
        val hc: Boolean

        if (t_ss1.lowEnd == t_ss2.lowEnd) {
            le = t_ss1.lowEnd
            lc = t_ss1.lowClosed && t_ss2.lowClosed
        } else if (t_ss1.lowEnd < t_ss2.lowEnd) {
            le = t_ss2.lowEnd
            lc = t_ss2.lowClosed
        } else {
            le = t_ss1.lowEnd
            lc = t_ss1.lowClosed
        }
        if (t_ss1.highEnd == t_ss2.highEnd) {
            he = t_ss1.highEnd
            hc = t_ss1.highClosed && t_ss2.highClosed
        } else if (t_ss1.highEnd > t_ss2.highEnd) {
            he = t_ss2.highEnd
            hc = t_ss2.highClosed
        } else {
            he = t_ss1.highEnd
            hc = t_ss1.highClosed
        }

        if (le > he) return EmptySet
        if (le == he && !(lc && hc)) return EmptySet
        var f: Calculatable? = null
        if (t_ss1 is MultipleOfSet) f = t_ss1.factor
        if (t_ss2 is MultipleOfSet) f = if (f == null) t_ss2.factor; else t_ss2.factor * f
        if (f == null) return realSet(le, he, lc, hc)
        return multipleOfSet(f!!, realSet(le, he, lc, hc))
    }

    override fun hasCommonElementsWith(other: net.pureal.traits.math.Set): Boolean {
        //TODO
        return false
    }

    override fun contains(other: Number): Boolean {
        return other in superset1 && other in superset2
    }

    override fun equals(other: Any?): Boolean = other is SetIntersection &&
            ((superset1 == other.superset1 && superset2 == other.superset2) || (superset2 == other.superset1 && superset1 == other.superset2))
}

val setIntersection = SetIntersection