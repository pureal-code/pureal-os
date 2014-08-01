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

    fun simplifySets(): Set {
        var s_ss1: Set = superset1
        var s_ss2: Set = superset2
        when(s_ss1) {
            is SetIntersection -> s_ss1 = (s_ss1 as SetIntersection).simplifySets()
            is SetUnion -> s_ss1 = (s_ss1 as SetIntersection).simplifySets()
            EmptySet -> return EmptySet
        }
        when(s_ss2) {
            is SetIntersection -> s_ss2 = (s_ss2 as SetIntersection).simplifySets()
            is SetUnion -> s_ss2 = (s_ss1 as SetIntersection).simplifySets()
            EmptySet -> return EmptySet
        }
        if(s_ss1 !is RealSet || s_ss2 !is RealSet) return setIntersection(s_ss1, s_ss2)
        val t_ss1: RealSet = s_ss1 as RealSet
        val t_ss2: RealSet = s_ss2 as RealSet

        val le: Calculatable
        val he: Calculatable
        val lc: Boolean
        val hc: Boolean

        if(t_ss1.lowEnd == t_ss2.lowEnd) {
            le = t_ss1.lowEnd
            lc = t_ss1.lowClosed && t_ss2.lowClosed
        } else if (t_ss1.lowEnd < t_ss2.lowEnd){
            le = t_ss2.lowEnd
            lc = t_ss2.lowClosed
        } else {
            le = t_ss1.lowEnd
            lc = t_ss1.lowClosed
        }
        if(t_ss1.highEnd == t_ss2.highEnd) {
            he = t_ss1.highEnd
            hc = t_ss1.highClosed && t_ss2.highClosed
        } else if (t_ss1.highEnd < t_ss2.highEnd){
            he = t_ss2.highEnd
            hc = t_ss2.highClosed
        } else {
            he = t_ss1.highEnd
            hc = t_ss1.highClosed
        }

        if(le > he) return EmptySet
        if(le == he && !(lc && hc)) return EmptySet

        // TODO: A Clever solution for this would be nice
        if(t_ss1 is IntegerSet || t_ss2 is IntegerSet) return integerSet(le, he, lc, hc)
        return realSet(le,he,lc,hc)
    }

    override fun hasCommonElementsWith(other: Set): Boolean {
        //TODO
        return false
    }

    override fun contains(other: Number): Boolean {
        return other in superset1 && other in superset2
    }
}

val setIntersection = SetIntersection