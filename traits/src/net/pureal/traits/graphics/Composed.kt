package net.pureal.traits.graphics

import net.pureal.traits.math.*
import net.pureal.traits.*

trait Composed<T> : Element<T>, ObservableIterable<Element<*>> {
    fun elementsAt(location: Vector2): Iterable<Element<*>> = this flatMap {
        val transformedLocation = it transform location
        val contains = it.shape.contains(transformedLocation)

        if (!contains) listOf<Element<*>>() else if (it is Composed) it.elementsAt(transformedLocation) else listOf(it)
    }
}

fun composed<T>(
        content: T,
        elements: ObservableIterable<Element<*>>,
        transform: Transform2 = Transforms2.identity,
        changed: Observable<Unit> = observable<Unit>()) = object : Composed<T> {
    override val content = content
    override val added = elements.added
    override val removed = elements.removed
    override fun iterator() = elements.iterator()
    override val shape = concatenatedShape(elements map { it.shape })
    override val transform = transform
    override val changed = changed
}

fun composed(
        elements: ObservableIterable<Element<*>>,
        transform: Transform2 = Transforms2.identity,
        changed: Observable<Unit> = observable<Unit>()): Composed<Unit> = composed(Unit.VALUE, elements, transform, changed)

fun observableIterable<T>(
        elements: Iterable<T>,
        added: Observable<T> = observable<T>(),
        removed: Observable<T> = observable<T>()) = object : ObservableIterable<T> {
    override val added = added
    override val removed = removed
    override fun iterator() = elements.iterator()
}