package net.pureal.android.app

import android.app.Activity
import android.os.Bundle
import net.pureal.android.backend.GlScreen
import net.pureal.shell.Shell
import net.pureal.traits.graphics.observableIterable
import net.pureal.android.backend.GlFont
import net.pureal.traits.interaction.*

class PurealActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screen = GlScreen(this) { Shell(it, observableIterable(listOf(it.pointerKeys)), observableIterable(listOf()), GlFont(getResources()!!)); }
        setContentView(screen);
    }
}