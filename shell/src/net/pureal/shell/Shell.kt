package net.pureal.shell

import net.pureal.traits.interaction.*
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import net.pureal.traits.*
import java.util.Date

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
            val text = """Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam
nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat,
sed diam voluptua. At vero eos et accusam et justo duo dolores et ea
rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem
ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing
elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna
aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo
dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus
est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur
sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et
dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam
et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea
takimata sanctus est Lorem ipsum dolor sit amet. AYA �¶Ѽ†◊²³"""
            val t = transformedElement(
                    textElement(text, font, size = 60, fill = Fills.solid(Colors.white)),
                    object: Transform2 {
                        override val matrix : Matrix3 get() = (Transforms2.translation(vector(-1000, 400)) before Transforms2.rotation(Date().getTime()*0.0005) before Transforms2.scale(.2+Math.pow(1+Math.pow(Math.sin(Date().getTime()*0.0003),5.0),4.0))).matrix
                    }
            )
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




