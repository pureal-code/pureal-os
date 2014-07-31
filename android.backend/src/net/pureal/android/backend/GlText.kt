package net.pureal.android.backend

import net.pureal.traits.graphics.TextElement
import net.pureal.traits.graphics.Font
import net.pureal.traits.graphics.Fill
import net.pureal.traits.math.Shape
import android.content.res.Resources
import java.util.HashMap
import java.io.InputStreamReader
import net.pureal.traits.Vector2

//TODO: override val original (runtime compiler barf)
class GlTextElement(val originalText: TextElement, screen: GlScreen) : TextElement, GlColoredElement(originalText, screen) {
    override val shape: GlShape get() = super<GlColoredElement>.shape
    override val font: GlFont get() = glFont(originalText.font)
    override val size: Number get() = originalText.size
    override val fill: Fill get() = originalText.fill
    override val content: String get() = originalText.content
}

class GlFont(resources: Resources) : Font {

    class Page(val id: Int, val file: String)
    class Char(val id: Int, val x: Int, val y: Int, val width: Int, val height: Int,
               val xOffset: Int, val yOffset: Int, val xAdvance: Int, val page: Int)

    val pages = HashMap<Int, Page>()
    val chars = HashMap<Int, Char>()
    val kernings = HashMap<Int, HashMap<Int, Int>>();

    {
        val stream = resources.openRawResource(R.raw.roboto_regular)
        try {
            val reader = InputStreamReader(stream, "UTF-8")
            reader.forEachLine {
                val entries = (it.split(' ') filter { it.any() }).toList()
                if (entries.any()) {
                    val tag = entries[0]
                    val values = (entries drop 1 map {
                        val strings = it.split("=")
                        Pair(strings.first(), strings.last())
                    }).toMap()
                    // TODO: use "when" instead (causes compiler barf right now)
                    val id = values["id"]?.toInt() ?: 0
                    if (tag == ("page")) {
                        pages.put(id, Page(id, values["file"] ?: ""))
                    } else if (tag == ("char")) {
                        chars.put(id, Char(id,
                                values["x"]?.toInt() ?: 0, values["y"]?.toInt() ?: 0,
                                values["width"]?.toInt() ?: 0, values["height"]?.toInt() ?: 0,
                                values["xoffset"]?.toInt() ?: 0, values["yoffset"]?.toInt() ?: 0,
                                values["xadvance"]?.toInt() ?: 0, values["page"]?.toInt() ?: 0))
                    } else if (tag == ("kerning")) {
                        val first = values["first"]?.toInt() ?: 0
                        val second = values["second"]?.toInt() ?: 0
                        val amount = values["amount"]?.toInt() ?: 0
                        val map = kernings.getOrPut(first) { -> HashMap<Int, Int>() }
                        map.put(second, amount)
                    }
                }
            }
        } finally {
            stream.close()
        }

    }

    override fun shape(text: String): Shape {
        return GlGlyphs(this, text)
    }

    private fun measureString(text: String): Number {
        return text.length // TODO: fix this
    }
}

fun glFont(original: Font): GlFont {
    return when (original) {
        is GlFont -> original
        else -> throw UnsupportedOperationException("No OpenGL implementation for font '${original}'.")
    }
}

class GlGlyphs(val font: GlFont, val text: String) : GlShape() {


    override val coordinates: FloatArray get() {
        val x = 1f
        val y = 1f
        val z = 0f
        return floatArray(
                +x, +y, z, // 0 top right
                -x, +y, z, // 1 top left
                -x, -y, z, // 2 bottom left
                +x, -y, z  // 3 bottom right
        )
    }
    override val drawOrder = shortArray(0, 1, 2, 3)

    override fun contains(location: Vector2): Boolean = false //TODO
}