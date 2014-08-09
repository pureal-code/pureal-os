package net.pureal.traits.math.sets

import net.pureal.traits.math.*
import net.pureal.traits.*

public trait MultipleOfSet : RealSet, Set {
    public class object : Constructor3<MultipleOfSet, Number, Number, Number>, Constructor2<MultipleOfSet, Number, RealSet> {
        override fun invoke(factor: Number, lEnd: Number, hEnd: Number): MultipleOfSet {
            var le = lEnd.asCalculatable()
            var he = hEnd.asCalculatable()
            if(le > he) {
                val tmp = le
                le = he
                he = tmp
            }
            try {
                le = le.ceil() * factor
            } catch(e: Exception) {
                // leave le as it is
            }
            try {
                he = he.floor() * factor
            } catch(e: Exception) {
                // leave he as it is
            }
            return object : MultipleOfSet {
                override val factor = factor.asCalculatable()
                override val lowEnd = le
                override val highEnd = he
            }
        }
        override fun invoke(factor: Number, s: RealSet): MultipleOfSet = invoke(factor, s.lowEnd, s.highEnd)
    }

    override fun toString(): String {
        var le = lowEnd
        var he = highEnd
        try {
            le = le / factor
        } catch(e: Exception) {
            // leave le as it is
        }
        try {
            he = he / factor
        } catch(e: Exception) {
            // leave he as it is
        }
        return "multipleOfSet(${factor}, ${le}, ${he})"
    }

    override fun contains(other: Number): Boolean {
        try{
            val o = other.asCalculatable() % factor
            return super<RealSet>.contains(other) && o equals 0
        } catch(e: UnsupportedOperationException) {
            return false
        } catch(e: RuntimeException) {
            return false
        }
    }

    override fun contains(other: Set): Boolean {
        if(other is MultipleOfSet) return lowEnd <= other.lowEnd && highEnd >= other.lowEnd && other.factor % factor equals 0
        return super<Set>.contains(other)
    }

    val factor: Calculatable

    override val lowClosed: Boolean get() = true
    override val highClosed: Boolean get() = true

    override fun equals(other: Any?) = other is MultipleOfSet && factor == other.factor && lowEnd == other.lowEnd && highEnd == other.highEnd

}

val multipleOfSet = MultipleOfSet