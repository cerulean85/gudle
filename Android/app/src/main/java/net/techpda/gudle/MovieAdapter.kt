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

class MovieAdapter(val context: Context, var list: ArrayList<Movie> = arrayListOf()): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = list[position]
        holder.tvTitle.text = movie.title
        holder.tvYear.text = "${movie.year}"
//        holder.ivThumb.setImageDrawable(movie.image)
        holder.ivThumb.setBackgroundColor(movie.color)

        Picasso.get().load(movie.image).transform(BlurTransformation(context, 25)).into(holder.ivThumb)

        //blur도 된다 -_-;;
        //https://dwfox.tistory.com/48
        //https://github.com/hendraanggrian/pikasso/tree/master/pikasso-transformations/src/com/hendraanggrian/pikasso/transformations

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

