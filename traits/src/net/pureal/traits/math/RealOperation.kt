package net.pureal.traits.math


public trait RealOperation : Real {
    val priority : Int

    val description : String

    override fun toString() : String

    fun getEncapsulatedString(outerPriority : int) : String {
        val s = this.toString()
        return if (priority > outerPriority) s; else "( ${s} )"
    }

}