package net.techpda.gudle.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_one.view.*
import net.techpda.gudle.Banner
import net.techpda.gudle.R
import android.provider.MediaStore
import android.graphics.Bitmap
import android.net.Uri
import org.jetbrains.anko.runOnUiThread


class HomeBannerListAdapter(private val mContext: Context, var list: ArrayList<Banner> = arrayListOf()) : PagerAdapter() {


    override fun instantiateItem(collection: ViewGroup, position: Int): Any {

        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(R.layout.layout_one, collection, false) as ViewGroup

        mContext.runOnUiThread {
            Picasso.get()
                    .load(list[position].urlThumb)
//                .transform(BlurTransformation(context, 25))
                    .into(layout.marketingIV)

            collection.addView(layout)
        }

        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        mContext.runOnUiThread {
            collection.removeView(view as View)
        }
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}