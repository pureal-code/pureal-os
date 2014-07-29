package net.pureal.traits.graphics

import net.pureal.traits.math.*
import net.pureal.traits.*

trait TransformedElement<T> : Element<T> {
    val transform: Transform2
}

fun transformedElement<T>(element : Element<T>, transform : Transform2 = Transforms2.identity) : TransformedElement<T> = object : TransformedElement<T>, Element<T> by element {
    override val transform = transform
}

trait Composed<T> : Element<T>, ObservableIterable<TransformedElement<*>> {
    fun elementsAt(location: Vector2): Iterable<TransformedElement<*>> = this flatMap {
        val transformedLocation = it transform location
        val contains = it.shape.contains(transformedLocation)

        if (!contains) listOf<TransformedElement<*>>() else if (it is Composed<*>) it.elementsAt(transformedLocation) map {x -> transformedElement(it, it.transform before x.transform)} else listOf(it)}
}

fun composed<T>(
        content: T,
        elements: ObservableIterable<TransformedElement<*>>,
        changed: Observable<Unit> = observable<Unit>()) = object : Composed<T> {
    override val content = content
    override val added = elements.added
    override val removed = elements.removed
    override fun iterator() = elements.iterator()
    override val shape = concatenatedShape(elements mapObservable { it.shape })
    override val changed = changed
}

fun composed(elements: ObservableIterable<TransformedElement<*>>, changed: Observable<Unit> = observable<Unit>()) = composed(Unit.VALUE, elements, changed)

fun observableIterable<T>(
        elements: Iterable<T>,
        added: Observable<T> = observable<T>(),
        removed: Observable<T> = observable<T>()) = object : ObservableIterable<T> {
    override val added = added
    override val removed = removed
    override fun iterator() = elements.iterator()
}