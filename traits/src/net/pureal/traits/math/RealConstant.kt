package net.pureal.traits.math

public trait RealConstant : Symbol, RealPrimitive {
    override val value: InternalReal get() = approximate()

    override fun toString(): String = super<Symbol>.toString()

    override fun equals(other: Any?): Boolean = super<Symbol>.equals(other) || (other is RealConstant && value == other.value)

    override fun approximate(): InternalReal {
        return calculation_fn().approximate()
    }

    protected val calculation_fn: (() -> Real)

    override val isApproximate: Boolean get() = calculation_fn().isApproximate

}

fun realConstant(n: String, u: String? = null, fn: (() -> Real)): RealConstant = object : RealConstant, Number() {
    override val name: String = n
    override val unit: String? = u
    override val calculation_fn = fn
}

