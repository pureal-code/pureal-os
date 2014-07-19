package net.pureal.traits.math

public trait RealConstant : Symbol, RealPrimitive {
    override val value: InternalReal get() = approximate()

    override fun toString(): String = super<Symbol>.toString()

    override val isKnown: Boolean get() = true

    override fun approximate(): InternalReal {
        return calculation_fn.invoke().approximate()
    }

    protected val calculation_fn: (() -> Real)

    override val isApproximate: Boolean get() = false

}

fun realConstant(n: String, u: String? = null, fn: (() -> Real)): RealConstant = object : RealConstant {
    override val name: String = n
    override val unit: String? = u
    override val calculation_fn = fn
}

