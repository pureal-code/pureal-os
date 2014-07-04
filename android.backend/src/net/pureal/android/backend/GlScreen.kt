package net.pureal.android.backend

import android.opengl.GLSurfaceView
import android.opengl.GLSurfaceView.Renderer
import javax.microedition.khronos.opengles.GL10
import javax.microedition.khronos.egl.EGLConfig
import android.opengl.GLES20
import android.app.Activity
import net.pureal.traits.graphics.*
import net.pureal.traits.*
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

class GlScreen(activity: Activity) : GLSurfaceView(activity), Screen {
    {
        setEGLContextClientVersion(2)
        setEGLConfigChooser(8, 8, 8, 8, 16, 0)
        setRenderer(object : Renderer {
            override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
                GLES20.glClearColor(0.0f, 0.0f, 1.0f, 1.0f)
                GLES20.glUseProgram(program);
            }
            override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
                GLES20.glViewport(0, 0, width, height)
            }
            override fun onDrawFrame(gl: GL10?) {
                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
                content.draw()
            }
        })
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY)
    }
    val program = createProgram()
    var content: GlComposedElement = GlComposedElement(composed(setOf()))
    override val size: Vector2 = vector(1, 1)
    override fun show(content: Composed) {
        when (content) {
            is GlComposedElement -> {
                this.content = content
            }
            else -> show(GlComposedElement(content))
        }
    }
}

fun createProgram(): Int {
    val vertexShaderCode = """
                    attribute vec4 vPosition;
                    void main() {
                        gl_Position = vPosition;
                    }
                """
    val fragmentShaderCode = """
                    precision mediump float;
                    uniform vec4 vColor;
                    void main() {
                        gl_FragColor = vColor;
                    }
                """
    val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
    val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)
    val program = GLES20.glCreateProgram()
    GLES20.glAttachShader(program, vertexShader)
    GLES20.glAttachShader(program, fragmentShader)
    GLES20.glLinkProgram(program)
    return program
}

fun loadShader(shaderType: Int, shaderCode: String): Int {
    val shader = GLES20.glCreateShader(shaderType)
    GLES20.glShaderSource(shader, shaderCode)
    GLES20.glCompileShader(shader)
    return shader;
}

class GlComposedElement(original: Composed, screen: GlScreen) : GlElement(original as? Element, screen), ComposedElement {
    override val elements: MutableSet<GlElement> = (original.elements map { glElement(it, screen) }).toHashSet()
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
    override val coordinates = floatArray()
    override val drawOrder = shortArray()
    override fun draw() = elements forEach { it.draw() }
}

fun glElement(original: Element, screen: GlScreen): GlElement {
    return when (original) {
        is GlElement -> original
        is Composed -> GlComposedElement(original, screen)
        is Rectangle -> GlRectangle(original)
        else -> throw UnsupportedOperationException("No OpenGL implementation for element '${original}'.")
    }
}

abstract class GlElement(open val original: Element?, val screen : GlScreen) : Element {
    override val transform: Transform2 get() = (original as? Element)?.transform ?: Transforms2.identity
    override val changed: Observable<Unit> get() = (original as? Element)?.changed ?: observable()
    abstract val coordinates: FloatArray
    abstract val drawOrder: ShortArray
    private var vertexBuffer = buildVertexBuffer()
    private var drawListBuffer = buildDrawListBuffer()
    fun buildVertexBuffer(): FloatBuffer {
        // 4 bytes per float
        val byteBuffer = ByteBuffer.allocateDirect(coordinates.size * 4) order ByteOrder.nativeOrder()!!;
        val floatBuffer = byteBuffer.asFloatBuffer()
        floatBuffer put coordinates position 0
        return floatBuffer
    }
    fun buildDrawListBuffer(): ShortBuffer {
        // 2 bytes per short
        val byteBuffer = ByteBuffer.allocateDirect(coordinates.size * 2) order ByteOrder.nativeOrder()!!;
        val shortBuffer = byteBuffer.asShortBuffer()
        shortBuffer put drawOrder position 0
        return shortBuffer
    }
    {
        changed += {
            vertexBuffer = buildVertexBuffer()
            drawListBuffer = buildDrawListBuffer()
            // how? redraw()
        }
    }
    open fun draw() {
        val positionHandle = GLES20.glGetAttribLocation(screen.program, "vPosition")
        GLES20.glEnableVertexAttribArray(positionHandle)
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 3*4, vertexBuffer)
        val colorHandle = GLES20.glGetUniformLocation(screen.program, "vColor")
        GLES20.glUniform4fv(colorHandle, 1, floatArray(1f, 0f, 0f, 1f), 0)
        GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, 0, drawOrder.size, drawListBuffer)
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

}

class GlRectangle(override val original: Rectangle, screen : GlScreen) : GlElement(original, screen), Rectangle {
    override val coordinates: FloatArray get() {
        val x = size.x.toFloat() / 2
        val y = size.y.toFloat() / 2
        val z = 0f
        return floatArray(
                +x, +y, z, // 0 top right
                -x, +y, z, // 1 top left
                -x, -y, z, // 2 bottom left
                +x, -y, z  // 3 bottom right
        )
    }
    override val drawOrder = shortArray(0, 1, 2, 3)
    override val size: Vector2 get() = original.size
    override val fill: Fill = null!!
    override val stroke: Stroke = null!!
}