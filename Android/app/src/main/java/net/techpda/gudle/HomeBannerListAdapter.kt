package net.techpda.gudle

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_one.view.*

class HomeBannerListAdapter(private val mContext: Context, var list: ArrayList<Banner> = arrayListOf()) : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {

        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(R.layout.layout_one, collection, false) as ViewGroup

        Picasso.get()
                .load(list[position].urlThumb)
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