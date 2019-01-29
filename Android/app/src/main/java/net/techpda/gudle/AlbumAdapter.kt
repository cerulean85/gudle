package net.techpda.gudle

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.image
import net.techpda.gudle.MainActivity
import org.jetbrains.anko.textColor
import android.widget.Toast



class AlbumAdapter  (val context: Context, var list: ArrayList<Album> = arrayListOf()): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(MovieUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = list[position]
        holder.tvTitle.text = album.title
        holder.tvViewCount.text = "조회수 " + album.countView//"${movie.year}"
//        holder.ivThumb.setImageDrawable(movie.image)
//        holder.ivThumb.setBackgroundColor(movie.color)


        Picasso.get()
            .load(album.urlThumb)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
                //.transform(BlurTransformation(context, 25))
            .into(holder.ivThumb)

        holder.view!!.setOnClickListener {

            App.binder.getCourseDetail(album.noCourse.toString()) {
                startActivity(context, Intent(context, CourseOverviewActivity::class.java), null)
            }
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
            tvTitle = itemView.findViewById(MovieUI.idTVTitle)
            tvViewCount = itemView.findViewById(MovieUI.idTVCountView)
            ivThumb = itemView.findViewById(MovieUI.idIVImage)
            view = itemView

        }

    }
}

