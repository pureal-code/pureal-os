package net.pureal.traits.graphics

import net.pureal.traits.math.*
import net.pureal.traits.*
import java.util.SortedSet

trait Composed : Element {
    val elements : Iterable<Element>
    fun elementsAt(location : Vector2) : Iterable<Element> = elements filter {it.shape.contains(it transform location)}
    val added : Observable<Element>
    val removed : Observable<Element>
}

fun composed(elements : Set<Element>, transform : Transform2) = object : Composed {
    override val shape = concatenatedShape(elements map {it.shape})
    override val elements = elements
    override val transform = transform
    override val added: Observable<Element> = observable<Element>()
    override val removed: Observable<Element> = observable<Element>()
    override val changed: Observable<Unit> = observable<Unit>()
}