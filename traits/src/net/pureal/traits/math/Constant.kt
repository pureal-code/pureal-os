package net.pureal.traits.math

public trait RealConstant : Symbol {
    protected val value : Real

    override val isKnown : Boolean get() = true

    override fun Number() : Number = approximate().Number()

    override fun approximate(): Real {
        if(calculation_fn != null) return calculation_fn!!.invoke()
        return value
    }

    protected val calculation_fn : (() -> Real)?

}

fun realConstant(n : String, v : Real, u: String? = null, fn: (() -> Real)? = null) : RealConstant = object : RealConstant {
    override protected val value : Real = v
    override val name : String = n
    override val unit : String? = u
    override val isApproximate : Boolean = fn != null
    override val calculation_fn = fn
}

