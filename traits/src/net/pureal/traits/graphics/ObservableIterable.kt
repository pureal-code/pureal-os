package net.pureal.traits.graphics

import net.pureal.traits.Observable
import net.pureal.traits.observable

trait ObservableIterable<out T> : Iterable<T> {
    val added: Observable<T>
    val removed: Observable<T>

    fun mapObservable<O>(transform: (T) -> O): ObservableIterable<O> = object : ObservableIterable<O> {
        override val removed = observable(this@ObservableIterable.removed, transform)
        override val added = observable(this@ObservableIterable.added, transform)
        override fun iterator() = (this@ObservableIterable map transform).iterator()
    }
}

fun <T> ObservableIterable<Observable<T>>.startKeepingAllObserved(observer: (T) -> Unit) {
    forEach { it addObserver observer }
    added addObserver { it addObserver observer }
    removed addObserver { it removeObserver observer }
}

fun <T> ObservableIterable<Observable<T>>.stopKeepingAllObserved(observer: (T) -> Unit) {
    forEach { it removeObserver observer }
    added removeObserver { it removeObserver observer }
    removed removeObserver { it removeObserver observer }
}