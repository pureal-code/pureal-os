package net.pureal.traits.math.sets
import net.pureal.traits.math.*

public trait EmptySet : Set {
    class object : EmptySet {
        val a = null
    }
    override fun contains(other: Number) = false
    override fun contains(other: Set) = false
    override fun hasCommonElementsWith(other: Set): Boolean = false
    override fun equals(other: Any?) = other is EmptySet

    override fun toString() = "EmptySet"
}