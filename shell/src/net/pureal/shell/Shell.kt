package net.pureal.shell

import net.pureal.traits.interaction.*
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import net.pureal.traits.*

// TODO brings reference barf: import org.jetbrains.jet.codegen.*

class Shell(val screen: Screen, val pointers: ObservableIterable<PointerKeys>, val keys: ObservableIterable<Key>, defaultFont: Font) {
    private val exampleContent = object : Any() {
        fun rotatedScaledRectangles() : Composed<*> {
            fun logoRect(angle : Number) = button(
                    shape = rectangle(vector(300, 100)) transformed (Transforms2.translation(vector(-70, 60)) before Transforms2.rotation(angle) before Transforms2.scale(0.5 * angle.toDouble())),
                    fill = Fills.solid(Colors.white),
                    onClick = {println("This is da fucking Pureal logo!")})

            fun star(count : Int) = count.indices map {transformedElement(logoRect(it * 3.14159 * 2 / count - Math.PI / 2))}

            return composed(elements = observableIterable(star(7)))
        }

        fun someText(font: Font) : Composed<*> {
            val text = """Lorem Ipsum is simply dummy text of the printing and typesetting industry.
Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
when an unknown printer took a galley of type and scrambled it to make a type
specimen book. It has survived not only five centuries, but also the leap into
electronic typesetting, remaining essentially unchanged. It was popularised in
the 1960s with the release of Letraset sheets containing Lorem Ipsum passages,
and more recently with desktop publishing software like Aldus PageMaker including
versions of Lorem Ipsum.

Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots
in a piece of classical Latin literature from 45 BC, making it over 2000 years
old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia,
looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum
passage, and going through the cites of the word in classical literature,
discovered the undoubtable source."""
            val t = transformedElement(textElement(text, font, size = 60, fill = Fills.solid(Colors.white)), Transforms2.translation(vector(-1100, 500)))
            //val k = transformedElement(textElement("Kotlin rocks!", font, size = 24, fill = Fills.solid(Colors.white)), Transforms2.rotation(-Math.PI / 10))
            //val h = transformedElement(textElement("like a hardcore banana", font, size = 24, fill = Fills.solid(Colors.white)), Transforms2.scale(0.5) before Transforms2.translation(vector(0,-(screen.shape as Rectangle).size.y.toDouble() / 3.0)))
            return composed(observableIterable(listOf(t)))//k, h)))
        }
    }

    {
        screen.content = exampleContent.someText(font = defaultFont)
        registerInputs()
    }

    fun registerInputs() = {
        pointers mapObservable {it.pressed} startKeepingAllObserved {p ->
            screen.content.elementsAt(p.pointer.location) forEach {
                if (it is Clickable<*>) it.onClick(pointerKey(p.pointer relativeTo it.transform, p.key))
            }
        }
    }
}




