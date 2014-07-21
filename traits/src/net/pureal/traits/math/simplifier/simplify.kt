package net.pureal.traits.math.simplifier

import net.pureal.traits.math.*

fun simplify(r: Real): Real {
    // TODO: do it, yeah
    var r: Real= r
    var r_back: Real
    do {
        r_back = r
        // TODO: transform shit
        r = r.calculate()
    } while (r_back != r)
    return r
}