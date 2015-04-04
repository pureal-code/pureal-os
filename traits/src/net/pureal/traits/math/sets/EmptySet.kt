package net.pureal.traits.math.sets

import net.pureal.traits.math.*

public trait EmptySet : net.pureal.traits.math.Set {
    class object : EmptySet {
        val a = null
    }
    override fun contains(other: Number) = false
    override fun contains(other: net.pureal.traits.math.Set) = false
    override fun hasCommonElementsWith(other: net.pureal.traits.math.Set): Boolean = false
    override fun equals(other: Any?) = other is EmptySet

    override fun toString() = "EmptySet"
}