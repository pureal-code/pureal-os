package net.pureal.traits.math

public trait RealConstant : Symbol {
    protected val value : Real

    override val isKnown : Boolean get() = true

    override fun Number() : Number = approximate().Number()

    override fun approximate(accuracy: Int) : Real {
        if(calculation_fn != null) return calculation_fn!!.invoke(accuracy)
        return value
    }

    protected val calculation_fn : ((Int) -> Real)?

}

fun realConstant(n : String, v : Real, u: String? = null, fn: ((Int) -> Real)? = null) : RealConstant = object : RealConstant {
    override protected val value : Real = v
    override val name : String = n
    override val unit : String? = u
    override val isApproximate : Boolean = fn != null
    override val calculation_fn = fn

}

