package net.pureal.traits.math

public trait Set {
    fun contains(other: Set): Boolean
    fun contains(other: Number): Boolean
}