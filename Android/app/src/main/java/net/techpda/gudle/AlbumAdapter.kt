package net.techpda.gudle

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.image
import net.techpda.gudle.MainActivity

class AlbumAdapter(val context: Context, var list: ArrayList<Album> = arrayListOf(), var imageArr: ArrayList<Image>): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(MovieUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val movie = list[position]
        holder.tvTitle.text = movie.title
        holder.tvYear.text = movie.date//"${movie.year}"
//        holder.ivThumb.setImageDrawable(movie.image)
//        holder.ivThumb.setBackgroundColor(movie.color)


        Picasso.get()
                .load("http://favorite.cafe24app.com/img/"+imageArr[movie.img].name)
                .placeholder(R.drawable.p1)
                .error(R.drawable.ic_launcher_background)
                .transform(BlurTransformation(context, 25))
                .into(holder.ivThumb)

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
        var tvYear: TextView
        var ivThumb: ImageView

        init {
            tvTitle = itemView.findViewById(MovieUI.idTVTitle)
            tvYear = itemView.findViewById(MovieUI.idTVDate)
            ivThumb = itemView.findViewById(MovieUI.idIVImage)

        }

    }
}

