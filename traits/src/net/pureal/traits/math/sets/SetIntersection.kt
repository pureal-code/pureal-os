package net.pureal.traits.math.sets

import net.pureal.traits.math.*
import net.pureal.traits.Constructor2

public trait SetIntersection : Set {
    class object : Constructor2<SetIntersection, Set, Set> {
        override fun invoke(ss1: Set, ss2: Set): SetIntersection = object : SetIntersection {
            override val superset1: Set = ss1
            override val superset2: Set = ss2
        }
    }

    val superset1: Set
    val superset2: Set

    override fun toString(): String = "setIntersection(${superset1},${superset2})"

    fun simplifySets(): Set {
        var s_ss1: Set = superset1
        var s_ss2: Set = superset2
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
        if (t_ss1 is MultipleOfSet) {
            if (t_ss2 is MultipleOfSet) {
                val g: Calculatable
                try {
                    g = gcd(t_ss1.factor, t_ss2.factor)
                    if (g equals 0) return EmptySet
                } catch (e: IllegalArgumentException) {
                    return EmptySet
                }
                if ((t_ss1.offset - t_ss2.offset) % g != 0.asCalculatable()) return EmptySet
                val f: Calculatable = lcm(t_ss1.factor, t_ss2.factor)
                // o1 + f1 * m = o2 + f2 * n = o
                var o: Calculatable = t_ss1.offset + f
                while (o > t_ss1.offset) {
                    if ((o - t_ss2.offset) % t_ss2.factor equals 0) break
                    o -= t_ss1.offset
                }
                // TODO: This isn't an elegant solution, but working for now
                return multipleOfSet(f, o, realSet(le, he, lc, hc))
            } else {
                return multipleOfSet(t_ss1.factor, t_ss1.offset, realSet(le, he, lc, hc))
            }
        } else if (t_ss2 is MultipleOfSet) {
            return multipleOfSet(t_ss2.factor, t_ss2.offset, realSet(le, he, lc, hc))
        } else return realSet(le, he, lc, hc)
        return EmptySet
    }

    override fun hasCommonElementsWith(other: Set): Boolean {
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