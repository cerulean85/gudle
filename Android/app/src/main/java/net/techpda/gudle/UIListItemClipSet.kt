package net.techpda.gudle

import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import org.jetbrains.anko.*

class UIListItemClipSet: AnkoComponent<ViewGroup>{

    companion object {
        val idTVTitle = 1
        val idIVImage = 3
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){

        verticalLayout {

            lparams(matchParent, wrapContent)
            linearLayout {
                lparams(matchParent, matchParent)

                relativeLayout {
                    lparams(matchParent, wrapContent)

                    imageView {
                        id = idIVImage
                        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }.lparams {
                        alignParentLeft()
                        alignParentTop()
                    }

                    verticalLayout {
                        layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)

                        textView {
                            id = idTVTitle
                            layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                            ellipsize = TextUtils.TruncateAt.END
                            maxLines = 1
                            textColor = Color.BLACK
                            textSize = 14f
                        }

                    }.lparams {
                        rightOf(idIVImage)
                        centerVertically()
                    }
                }
            }

        }

    }
}