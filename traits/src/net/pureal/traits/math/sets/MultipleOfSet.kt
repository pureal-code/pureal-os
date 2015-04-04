package net.pureal.traits.math.sets

import net.pureal.traits.math.*
import net.pureal.traits.*

public trait MultipleOfSet : RealSet, net.pureal.traits.math.Set {
    public class object :
            Constructor4<net.pureal.traits.math.Set, Number, Number, Number, Number>,
            Constructor3<net.pureal.traits.math.Set, Number, Number, Number>,
            Constructor2<net.pureal.traits.math.Set, Number, RealSet> {
        override fun invoke(factor: Number, offset: Number, lEnd: Number, hEnd: Number): net.pureal.traits.math.Set {
            val factor = factor.asCalculatable().abs()
            // to make 0 <= offset < factor
            val off = offset divideAndRemainder factor
            if (factor is Infinity) return EmptySet
            var le = lEnd.asCalculatable() + off[0]
            var he = hEnd.asCalculatable() + off[0]
            if (le > he) {
                val tmp = le
                le = he
                he = tmp
            }
            le = le.ceil() * factor + off[1]
            he = he.floor() * factor + off[1]
            return object : MultipleOfSet {
                override val factor = factor
                override val offset = off[1]
                override val lowEnd = le
                override val highEnd = he
            }
        }
        override fun invoke(factor: Number, lEnd: Number, hEnd: Number): net.pureal.traits.math.Set = invoke(factor, 0, lEnd, hEnd)
        override fun invoke(factor: Number, s: RealSet): net.pureal.traits.math.Set = invoke(factor, s.lowEnd, s.highEnd)
    }

    override fun toString(): String = "multipleOfSet(${factor}, ${lowEnd / factor}, ${highEnd / factor})"

    override fun contains(other: Number): Boolean {
        try {
            val o = (other.asCalculatable() - offset) % factor
            return super<RealSet>.contains(other) && o equals 0
        } catch(e: UnsupportedOperationException) {
            return false
        }
    }

    override fun contains(other: net.pureal.traits.math.Set): Boolean {
        if (other is MultipleOfSet)
            return lowEnd <= other.lowEnd && highEnd >= other.lowEnd
                    && other.factor % factor equals 0 && (other.offset - offset) % factor equals 0
        return super<net.pureal.traits.math.Set>.contains(other)
    }

    val factor: Calculatable
    val offset: Calculatable

    override val lowClosed: Boolean get() = true
    override val highClosed: Boolean get() = true

    override fun equals(other: Any?) = other is MultipleOfSet && factor == other.factor && lowEnd == other.lowEnd && highEnd == other.highEnd

}

val multipleOfSet = MultipleOfSet