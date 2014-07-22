package net.pureal.traits

trait Observable<out T> {
    protected val observers: MutableSet<(T) -> Unit> get() = hashSetOf()
    final fun plusAssign(observer: (T) -> Unit) {
        observers.add(observer)
    }
    final fun minusAssign(observer: (T) -> Unit) {
        observers.remove(observer)
    }
    final protected fun notifyObservers(info: T) {
        for (observer in observers) {
            observer(info)
        }
    }
}

fun observable<T, I>(observables: Iterable<Observable<I>>, map : (I)->T) = object : Observable<T> {
    {
        for (observable in observables) {
            observable += { notifyObservers(map(it)) }
        }
    }
}

fun observable<T>(observables: Iterable<Observable<T>>) : Observable<T> = observable(observables) { it }

fun observable<T>(vararg observables: Observable<T>)  : Observable<T> = observable(object : Iterable<Observable<T>> { override fun iterator() = observables.iterator() })
