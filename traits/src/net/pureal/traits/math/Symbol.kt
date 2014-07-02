package net.pureal.traits.math

public trait Symbol : Real {
    val name : String

    // TODO: Evaluate if isKnown is needed on the whole Real trait
    val isKnown : Boolean get() = false

    val unit : String? // TODO: Create a dedicated Unit Type

    override fun toString() = name

    override val isPrimitive : Boolean get() = isKnown



}