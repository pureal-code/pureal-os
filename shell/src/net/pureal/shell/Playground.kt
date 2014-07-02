package net.pureal.shell

import net.pureal.traits.graphics.*
import net.pureal.traits.*
import kotlin.properties.Delegates
/*
class Playground {
    fun foo() {

        val growingRect = object : Rectangle {
            var width: Double = 1.0
            var height : Double = 2.0
            override val size = object : Vector2 {
                override val x: Number get() = width
                override val y: Number get() = height
            }
            override val fill = object : SolidFill { override val color = colorOf(g=1) }
            override val stroke = object : InvisibleStroke {}
            override val transform = object : Transform2 {
                override val rotation = 0
                override val scale = vectorOf(0, 0)
                override val translation =  vectorOf(0, 0)
            }
            override val changed = triggerOf<Unit>()

            fun grow () {
                width *= 2
                height *= 2
                changed()
            }
        }

        growingRect.grow();

    }

    class Example : ComposedElement {
        override var transform by Delegates.observable(initial=transformOf()) { (meta, old, new) -> changed() }
        override val changed = triggerOf<Unit>()

        private val rectangle = object : Rectangle {
            override var size by Delegates.observable(initial=vectorOf(1,1)) { (meta, old, new) -> changed() }
            override val fill = object : Gradient {
                override val stops = mapOf(Pair(0, Colors.gray), Pair(1, Colors.white))
                override val transform  = transformOf()
            }
            override val stroke = object : InvisibleStroke {}
            override val transform = transformOf()
            override val changed : Trigger<Unit> = triggerOf()
        }
        private val text = object : Text {
            override var content by Delegates.observable(initial="Button") { (meta, old, new) -> changed() }
            override val transform = transformOf()
            override val changed : Trigger<Unit> = triggerOf()
        }

        var size = rectangle.size
        var content = text.content

        override val elements = setOf(rectangle, text)
        override val added = observableOf<Element>()
        override val removed = observableOf<Element>()
    }
}