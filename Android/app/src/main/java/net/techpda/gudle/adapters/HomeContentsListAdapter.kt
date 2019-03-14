package net.techpda.gudle.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import net.techpda.gudle.App
import net.techpda.gudle.Course
import net.techpda.gudle.R
import net.techpda.gudle.ViewBehavior
import net.techpda.gudle.activities.CourseOverviewActivity


class HomeContentsListAdapter  (val context: Context, var list: ArrayList<Course> = arrayListOf()): RecyclerView.Adapter<HomeContentsListAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {

//        return AlbumViewHolder(MovieUI().createView(AnkoContext.create(parent.context, parent)))
        return AlbumViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item_tab_home, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {

        val course = list[position]
        holder.tvTitle.text = course.title
        holder.tvViewCount.text = "조회수 " + course.countView//"${movie.year}"
//        holder.ivThumb.setImageDrawable(movie.image)
//        holder.ivThumb.setBackgroundColor(movie.color)


        course.urlThumb.let {

            if(!course.urlThumb.isNullOrEmpty()) {
                Picasso.get()
                        .load(course.urlThumb)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_background)
                        //.transform(BlurTransformation(context, 25))
                        .into(holder.ivThumb)
            }
        }

        holder.view!!.setOnClickListener {
            App.binder.getCourseDetail(course.no.toString())
            App.binder.getClipList(course.no.toString(),
            {
                startActivity(context, Intent(context, CourseOverviewActivity::class.java), null)
            })
        }



//        Picasso.get().load("https://post-phinf.pstatic.net/MjAxODA1MzBfNDkg/MDAxNTI3NjY3MzE3Nzc1.JGkOAHkv6UUS_4tqnD6irtAcztUHhYhmwP5xwME9H04g.SixBN61IDWoFV3gSwlgSUsSxtOtzcp2H_TQZzJmho3kg.JPEG/메인후보1.jpg?type=w1200").transform(BlurTransformation(context, 25)).into(holder.ivThumb)
        //blur도 된다 -_-;;
        //https://dwfox.tistory.com/48
        //https://github.com/hendraanggrian/pikasso/tree/master/pikasso-transformations/src/com/hendraanggrian/pikasso/transformations

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView
        var tvViewCount: TextView
        var ivThumb: ImageView
        var view: View? = null

        init {

//             LayoutInflater.from(context).inflate(R.layout.recycler_view_item_tab_home, null)

            tvTitle = itemView.findViewById(R.id.idTVTitle)
            tvViewCount = itemView.findViewById(R.id.idTVCountView)
            ivThumb = itemView.findViewById(R.id.idIVImage)
            view = itemView

            ViewBehavior.heightTabHomeContentItem = itemView.layoutParams.height

        }

    }
}

