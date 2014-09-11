package net.pureal.traits

fun <T> Array<T>.replaceElements(fn: (T) -> T): Array<T> {
    var t = this
    for (i in t.indices) t[i] = fn.invoke(t[i])
    return t
}

fun String.extractInnerString(): String {
    val begin = this.indexOf("\"")
    val end = this.lastIndexOf("\"")
    if (begin == end) return this
    return this.substring(begin + 1, end)
}

fun String.removeWhitespace() = this filter { !it.isWhitespace() }