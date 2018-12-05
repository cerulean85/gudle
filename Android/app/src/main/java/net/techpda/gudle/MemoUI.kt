package net.techpda.gudle

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import java.net.URL

/**
 * Created by abduaziz on 5/25/18.
 */

class MovieUI : AnkoComponent<ViewGroup>{

    companion object {
        val idTVTitle = 1
        val idTVDate = 2
        val idIVImage = 3
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){

        verticalLayout {
            topPadding = dip(10)
            bottomPadding = dip(10)
            leftPadding = dip(20)
            rightPadding = dip(20)
            lparams(matchParent, dip(100))

            linearLayout {
                lparams(matchParent, matchParent)
                gravity = Gravity.RIGHT

                verticalLayout {
                    lparams(matchParent, matchParent)
                    leftPadding = dip(90)
                    rightPadding = dip(10)
                    textView {
                        id = idTVTitle
                        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                        text = "untitled"
                        textSize = 16f // <- it is sp, no worries
                        textColor = Color.BLACK
                    }

                    textView {
                        id = idTVDate
                        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                        text = "1900-00-00"
                        textSize = 14f
                    }
                }

                imageView {
                    id = idIVImage
                    image = resources.getDrawable(R.drawable.p1)
                    layoutParams = LinearLayout.LayoutParams(dip(80), dip(80))

                }
            }
        }
    }
}