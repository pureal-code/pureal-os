package net.pureal.traits.graphics

import net.pureal.traits.math.*
import net.pureal.traits.*
import java.util.SortedSet

trait Element {
    val transform: Transform2
    val shape : Shape
    val changed : Observable<Unit>
}