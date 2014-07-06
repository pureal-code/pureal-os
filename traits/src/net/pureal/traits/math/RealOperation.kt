package net.pureal.traits.math


public trait RealOperation : Real {
    override val isPrimitive : Boolean
        get() = false

    val priority : Int

    val description : String


    override fun toString() : String

    override fun getOuterString(outerPriority : Int) : String {
        val s = this.toString()
        return if (priority > outerPriority) s; else "(${s})"
    }

    override fun getOuterMathString(outerPriority : Int) : String {
        val s = this.toMathematicalString()
        return if (priority > outerPriority) s; else "(${s})"
    }

}