package net.pureal.shell

import net.pureal.traits.graphics.*
import net.pureal.traits.*
import kotlin.properties.Delegates

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
            override val transform = object : Transform {
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

    class Label : Composed {
        override var transform by Delegates.observable(initial=transformOf()) { (meta, old, new) -> changed() }
        override val changed = triggerOf<Unit>()

        var size by Delegates.observable(initial=vectorOf(1,1)) { (meta, old, new) -> rectChanged() }
        var content by Delegates.observable(initial="Button") { (meta, old, new) -> textChanged() }

        private val rectChanged = triggerOf<Unit>()
        private val textChanged = triggerOf<Unit>()
        private val label = this
        private val rect = object : Rectangle {
            override val size : Vector2 get() = label.size
            override val fill = object : Gradient {
                override val stops = mapOf(Pair(0, Colors.gray), Pair(1, Colors.white))
                override val transform  = transformOf()
            }
            override val stroke = object : InvisibleStroke {}
            override val transform = transformOf()
            override val changed = rectChanged
        }
        private val text = object : Text {
            override val content : String get() = label.content
            override val transform = transformOf()
            override val changed = textChanged
        }

        override val elements = setOf(rect, text)
        override val added = observableOf<Element>()
        override val removed = observableOf<Element>()
    }
}