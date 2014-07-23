package net.pureal.traits.graphics

import net.pureal.traits.Observable
import net.pureal.traits.observable

trait ObservableIterable<out T> : Iterable<T> {
    val added: Observable<T>
    val removed: Observable<T>

    fun observables<O>(transform : (T) -> Observable<O>) : ObservableIterable<Observable<O>> = object : ObservableIterable<Observable<O>> {
        override val removed = observable(this@ObservableIterable.removed, transform)
        override val added = observable(this@ObservableIterable.added, transform)
        override fun iterator() = (this@ObservableIterable map {transform(it)}).iterator()
    }
}

fun <T> ObservableIterable<Observable<T>>.startKeepingAllObserved(observer: (T)->Unit) {
    forEach {it addObserver {observer(it)}}

    added addObserver {it addObserver {observer(it)}}
    removed addObserver {it removeObserver {observer(it)}}
}

fun <T> ObservableIterable<Observable<T>>.stopKeepingAllObserved(observer: (T)->Unit) {
    forEach {it removeObserver {observer(it)}}

    added removeObserver {it removeObserver {observer(it)}}
    removed removeObserver {it removeObserver {observer(it)}}
}