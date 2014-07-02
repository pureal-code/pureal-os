package net.pureal.android.app

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.opengl.GLSurfaceView.Renderer
import javax.microedition.khronos.opengles.GL10
import javax.microedition.khronos.egl.EGLConfig
import android.opengl.GLES20

class PurealActivity : Activity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(object : GLSurfaceView(this) {
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
        })
    }
}