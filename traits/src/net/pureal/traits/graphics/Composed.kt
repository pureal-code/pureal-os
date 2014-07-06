package net.pureal.traits.graphics

import net.pureal.traits.math.*
import net.pureal.traits.*
import java.util.SortedSet

trait Composed<T> : Element<T> {
    val elements : Iterable<Element<*>>
    fun elementsAt(location : Vector2) : Iterable<Element<*>> = elements flatMap {
        val transformedLocation = it transform location
        val contains = it.shape.contains(transformedLocation)

        if(!contains) listOf<Element<*>>() else if(it is Composed) it.elementsAt(transformedLocation) else listOf(it)
    }

    val added : Observable<Element<*>>
    val removed : Observable<Element<*>>
}

fun composed<T>(
        content : T,
        elements : Set<Element<*>>,
        transform : Transform2 = Transforms2.identity,
        added: Observable<Element<*>> = observable<Element<*>>(),
        removed: Observable<Element<*>> = observable<Element<*>>(),
        changed: Observable<Unit> = observable<Unit>()) = object : Composed<T> {
    override val content = content
    override val shape = concatenatedShape(elements map {it.shape})
    override val elements = elements
    override val transform = transform
    override val added = added
    override val removed = removed
    override val changed = changed
}