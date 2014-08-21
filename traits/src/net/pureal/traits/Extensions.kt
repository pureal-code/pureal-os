package net.pureal.traits

fun <T> Array<T>.replaceElements(fn: (T) -> T): Array<T> {
    var t = this
    for (i in t.indices) t[i] = fn.invoke(t[i])
    return t
}