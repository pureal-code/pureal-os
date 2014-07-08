package net.pureal.traits.math

public trait Symbol : Real {
    val name : String

    final override val priority : Int get() = 1000

    // TODO: Evaluate if isKnown is needed on the whole Real trait
    val isKnown : Boolean get() = false

    val unit : String? // TODO: Create a dedicated Unit Type

    override fun toString() = name
    override fun toMathematicalString() = name


}