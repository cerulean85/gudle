package net.techpda.gudle

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_one.view.*

class CustomPagerAdapter(private val mContext: Context, var list: ArrayList<Movie> = arrayListOf()) : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val modelObject = list[position]
        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(modelObject.layoutResId, collection, false) as ViewGroup

        Picasso.get()
                .load(list[position].image)
//                .transform(BlurTransformation(context, 25))
                .into(layout.marketingIV)


        collection.addView(layout)
        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

//    override fun getPageTitle(position: Int): CharSequence {
//        val customPagerEnum = list[position]
//        return mContext.getString(customPagerEnum.titleResId)
//    }
}