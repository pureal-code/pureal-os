package net.pureal.android.app

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.opengl.GLSurfaceView.Renderer
import javax.microedition.khronos.opengles.GL10
import javax.microedition.khronos.egl.EGLConfig
import android.opengl.GLES20
import net.pureal.android.backend.GlScreen
import net.pureal.shell.Shell

class PurealActivity : Activity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        val screen = GlScreen(this)
        setContentView(screen);
        Shell(screen);
    }
}