package net.techpda.gudle

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_main.view.*
import android.content.res.TypedArray
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.imageResource


class MainView: LinearLayout{

    constructor(context: Context): super(context) { initView() }
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) { initView(); getAttrs(attrs!!) }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) { initView(); getAttrs(attrs!!) }

    fun initView() {

        val infService = Context.LAYOUT_INFLATER_SERVICE
        val li = getContext().getSystemService(infService) as LayoutInflater
        val v = li.inflate(R.layout.view_main, this, false)
        addView(v)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MainView)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        setBG(typedArray.getResourceId(R.styleable.MainView_bg, R.drawable.p1))
        setSymbol(typedArray.getResourceId(R.styleable.MainView_symbol, R.drawable.lego))
        setTitle(typedArray.getString(R.styleable.MainView_text))
        typedArray.recycle()
    }

    fun setBG(id: Int) {
        baseView.backgroundResource = id
    }

    fun setSymbol(id: Int) {
        symbolView.imageResource = id

    }

    fun setTitle(str: String) {
        titleView.text = str
    }




}



//
//@JvmOverloads constructor(
//        context: Context,
//        attrs: AttributeSet? = null,
//        defStyleAttr: Int = 0
//) : LinearLayout(context, attrs, defStyleAttr) {
//    init {
//
//
//        val infService = Context.LAYOUT_INFLATER_SERVICE
//        val li = getContext().getSystemService(infService) as LayoutInflater
//        val v = li.inflate(R.layout.view_main, this, false)
//        addView(v)
//
//
//
//
//
////        val a = context.obtainStyledAttributes(attrs,
////                R.styleable.MainView, 0, 0)
////        val titleText = a.getString(R.styleable.MainView_titleText)
////        val valueColor = a.getColor(R.styleable.MainView_valueColor, resources.getColor(android.R.color.holo_blue_light))
////        a.recycle()
////
////        setOnClickListener {
////
////
////        }
//
////        LayoutInflater.from(context).inflate(R.layout.view_main, this, true)
//
////        if (attrs != null) {
////            val a = context.obtainStyledAttributes(attrs, R.styleable.custom_card_view)
////            if (a.hasValue(R.styleable.custom_card_view_command)) {
////                var myString = a.getString(R.styleable.custom_card_view_command)
////            }
////        }
//
////        sendButton.setOnClickListener ({ nameLabel.text = "클릭: " + System.currentTimeMillis() })
//    }
//}