package com.poweramp.v3.sampleskin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.maxmpz.poweramp.player.PowerampAPIHelper.getPowerampPackageName

/** Returns the installed Poweramp package or shows an error toast */
internal fun Context.getPaPak(): String? {
    val pak = getPowerampPackageName(this, false)
    if(pak == null) {
        Toast.makeText(this, R.string.skin_poweramp_not_installed, Toast.LENGTH_LONG).show()
    }
    return pak
}

/** Launcher screen of the skin app. Only acts when the user taps a button. */
class SkinInfoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skin_info)
        findViewById<View>(R.id.reverify_button).visibility = View.GONE
    }

    /** Opens Poweramp with the default Glass Dark skin applied */
    fun startWithSampleSkin(view: View?) {
        val pak = getPaPak() ?: return
        val intent = Intent(Intent.ACTION_MAIN)
            .setClassName(pak, "com.maxmpz.audioplayer.StartupActivity")
            .putExtra("theme_pak", packageName)
            .putExtra("theme_id", R.style.GlassDark)
        startActivity(intent)
        finish()
    }

    /** Opens Poweramp's theme settings so the user can pick one of the Glass Dark variants */
    fun openPowerampThemeSettings(view: View?) {
        val pak = getPaPak() ?: return
        val intent = Intent(Intent.ACTION_MAIN)
            .setClassName(pak, "com.maxmpz.audioplayer.SettingsActivity")
            .putExtra("open", "theme")
        startActivityForResult(intent, 1) // startActivityForResult is required for open=theme
        finish()
    }

    fun startWithSampleAAASkin(view: View?) = startWithSampleSkin(view)

    fun reverifySkin(view: View?) {}
}
