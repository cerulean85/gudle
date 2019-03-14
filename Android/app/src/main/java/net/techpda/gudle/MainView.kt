package net.techpda.gudle

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_main.view.*
import android.content.res.TypedArray



class MainView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    init {


        val a = context.obtainStyledAttributes(attrs,
                R.styleable.MainView, 0, 0)
        val titleText = a.getString(R.styleable.MainView_titleText)
        val valueColor = a.getColor(R.styleable.MainView_valueColor, resources.getColor(android.R.color.holo_blue_light))
        a.recycle()


//        LayoutInflater.from(context).inflate(R.layout.view_main, this, true)

//        if (attrs != null) {
//            val a = context.obtainStyledAttributes(attrs, R.styleable.custom_card_view)
//            if (a.hasValue(R.styleable.custom_card_view_command)) {
//                var myString = a.getString(R.styleable.custom_card_view_command)
//            }
//        }

//        sendButton.setOnClickListener ({ nameLabel.text = "클릭: " + System.currentTimeMillis() })
    }
}