package net.pureal.traits.graphics

import net.pureal.traits.*
import net.pureal.traits.interaction.*
import net.pureal.traits.math.*

trait Screen {
    var content: Element<*>
    val shape: Shape
}