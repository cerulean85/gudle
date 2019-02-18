package net.techpda.gudle

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi

object Util {

    val version = Build.VERSION.SDK_INT
    var density: Float = 0F
    var heightDisplay: Int = 0
    var heightStatusBar: Int = 0

    @RequiresApi(Build.VERSION_CODES.M)
    fun getColor(context: Context, id: Int): Int {
        return when {
            version >= Build.VERSION_CODES.M -> context.getColor(id)
            else -> context.resources.getColor(id)
        }
    }

    fun getDrawable(context: Context, id: Int): Drawable {
        return when {
            version >= Build.VERSION_CODES.LOLLIPOP -> context.getDrawable(id)
            else -> context.resources.getDrawable(id)
        }
    }

    fun dp(value: Int): Int { return Math.round(value.toFloat() * density) }

}