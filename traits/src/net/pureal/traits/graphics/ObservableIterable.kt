package net.pureal.traits.graphics

import net.pureal.traits.Observable
import net.pureal.traits.observable
import java.util.ArrayList
import net.pureal.traits.trigger

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

fun <T> ObservableIterable<Observable<T>>.stopKeepingAllObserved(observer: (T)->Unit) {
    forEach {it removeObserver observer}
    added removeObserver {it removeObserver observer}
    removed removeObserver {it removeObserver observer}
}

trait ObservableList<T> : ObservableIterable<T>, MutableList<T>

fun observableList<T>(vararg values: T) : ObservableList<T> {
    val elements = ArrayList(values map {it})
    return object : MutableList<T> by elements, ObservableList<T> {
        override val removed = trigger<T>()
        override val added = trigger<T>()

        override fun add(e: T) : Boolean {
            elements.add(e)
            added(e)

            return true
        }

        override fun remove(o: Any?) : Boolean {
            if(!elements.remove(o)) return false

            removed(o as? T)

            return true
        }

        override fun removeAll(c : Collection<Any?>) = c.fold(initial=false) {(removedAny, it) -> remove(it) or removedAny}
    }
}
