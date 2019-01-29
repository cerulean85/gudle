package net.techpda.gudle

import android.content.Context
import android.widget.ImageView
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View


class ScaledImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val d = drawable

        if (d != null) {
            val width: Int
            val height: Int
            if (View.MeasureSpec.getMode(heightMeasureSpec) == View.MeasureSpec.EXACTLY) {
                height = View.MeasureSpec.getSize(heightMeasureSpec)
                width = Math.ceil((height * d.intrinsicWidth.toFloat() / d.intrinsicHeight).toDouble()).toInt()
            } else {
                width = View.MeasureSpec.getSize(widthMeasureSpec)
                height = Math.ceil((width * d.intrinsicHeight.toFloat() / d.intrinsicWidth).toDouble()).toInt()
            }
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}