package net.pureal.traits.math.sets

import net.pureal.traits.math.*

public trait SetUnion : net.pureal.traits.math.Set {
    val subset1: net.pureal.traits.math.Set
    val subset2: net.pureal.traits.math.Set

    override fun toString(): String = "setUnion(${subset1},${subset2})"

    fun simplifySets(): net.pureal.traits.math.Set {
        // TODO
        return this
    }

    override fun hasCommonElementsWith(other: net.pureal.traits.math.Set): Boolean {
        // TODO
        return false
    }
}