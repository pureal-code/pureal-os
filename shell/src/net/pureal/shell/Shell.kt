package net.pureal.shell

import net.pureal.traits.interaction.*
import net.pureal.traits.graphics.*
import net.pureal.traits.math.*
import net.pureal.traits.*
// TODO brings reference barf: import org.jetbrains.jet.codegen.*

class Shell(val screen: Screen) {{screen.content = composed(elements=setOf(button(shape=singleColored(rectangle(screen.size / 2), Colors.gray), onClick={println("button clicked!")})))}}