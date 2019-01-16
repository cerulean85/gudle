package net.techpda.gudle

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*
import java.net.URL

/**
 * Created by abduaziz on 5/25/18.
 */

class MovieUI : AnkoComponent<ViewGroup>{

    companion object {
        val idTVTitle = 1
        val idTVCountView = 2
        val idIVImage = 3
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){

        verticalLayout {

            lparams(matchParent, wrapContent) //(App.displayWidth * (640.0f/1242.0f)).toInt())



            linearLayout {
                lparams(matchParent, matchParent)

                verticalLayout {
                    lparams(matchParent, matchParent)
                    topPadding = dip(12)

                    imageView {
                        id = idIVImage
                        image = resources.getDrawable(R.drawable.p1)
                        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent) //(App.displayWidth * (460.0f/1242.0f)).toInt())
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }

                    relativeLayout {
                        lparams(matchParent, wrapContent)

                        textView {
                            id = idTVTitle
                            layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                            leftPadding = dip(16)
                            topPadding = dip(10)
                            maxWidth = (App.displayWidth * 0.70f).toInt()
                            ellipsize = TextUtils.TruncateAt.END
                            maxLines = 1
                            textColor = Color.BLACK
                            textSize = 14f
                        }

                        textView {
                            id = idTVCountView
                            layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                            textSize = 12f
                            rightPadding = dip(16)
                            topPadding = dip(10)
                            textColor = R.color.gray_87
                        }.lparams(wrapContent, dip(40)) {
                            centerVertically()
                            alignParentRight()
                        }
                    }
                }
            }
        }.applyRecursively { view ->
//            when (view) {
//                is Button -> view.backgroundColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
//                is TextView -> view.textColor = ContextCompat.getColor(ctx, R.color.colorPrimaryDark)
//            }
        }
    }
}