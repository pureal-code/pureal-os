package net.pureal.android.backend

import android.opengl.GLSurfaceView
import android.opengl.GLSurfaceView.Renderer
import javax.microedition.khronos.opengles.GL10
import javax.microedition.khronos.egl.EGLConfig
import android.opengl.GLES20
import android.app.Activity
import net.pureal.traits.graphics.*
import net.pureal.traits.*

class GlScreen(activity: Activity) : GLSurfaceView(activity), Screen {
    {
        setEGLContextClientVersion(2)
        setEGLConfigChooser(8, 8, 8, 8, 16, 0)
        setRenderer(object : Renderer {
            override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
                GLES20.glClearColor(0.0f, 0.0f, 1.0f, 1.0f)
            }
            override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
                GLES20.glViewport(0, 0, width, height)
            }
            override fun onDrawFrame(gl: GL10?) {
                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
            }
        })
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY)
    }
    var content: GlComposedElement = GlComposedElement(composed(setOf()))
    override val size: Vector2 = vector(1, 1)
    override fun show(content: Composed) {
        when (content) {
            is GlComposedElement -> this.content = content
            else -> show(GlComposedElement(content))
        }
    }
}

class GlComposedElement(original: Composed) : GlElement(original as? Element), ComposedElement {
    override val elements: MutableSet<GlElement> = (original.elements map ::glElement).toHashSet()
    override val added: Trigger<GlElement> = trigger()
    override val removed: Trigger<GlElement> = trigger();
    {
        original.added += {(addedElement) ->
            val glElement = glElement(addedElement)
            elements.add(glElement)
            added(glElement)
        }
        original.removed += {(removedElement) ->
            val glElement = (elements.singleOrNull { element -> element.original === removedElement })
            if (glElement != null) {
                elements.add(glElement)
                added(glElement)
            }
        }
    }
}

fun glElement(original: Element): GlElement {
    return when (original) {
        is GlElement -> original
        is Composed -> GlComposedElement(original)
        is Rectangle -> GlRectangle(original)
        else -> throw UnsupportedOperationException("No OpenGL implementation for element '${original}'.")
    }
}

abstract class GlElement(open val original: Element?) : Element {
    override val transform: Transform2 get() = (original as? Element)?.transform ?: Transforms2.identity
    override val changed: Observable<Unit> get() = (original as? Element)?.changed ?: observable()
    //vertexBuffer
}

class GlRectangle(override val original: Rectangle) : GlElement(original) {

}