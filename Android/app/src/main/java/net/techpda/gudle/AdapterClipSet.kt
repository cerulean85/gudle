package net.techpda.gudle

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

class AdapterClipSet(val context: Context, var list: ArrayList<Clip> = arrayListOf()): RecyclerView.Adapter<AdapterClipSet.HolderClipSet>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderClipSet {

        val v: View = LayoutInflater.from(context).inflate(R.layout.item_clip_set, parent,false)

        return HolderClipSet(v)
    }


    override fun onBindViewHolder(holder: HolderClipSet, position: Int) {
        val item = list[position]
        holder.tvTitle.text = item.title

        Picasso.get().load(item.urlImage01).into(holder.ivContent)

        holder.view.setOnClickListener {

            App.binder.getClipDetail(item.noContent.toString(), item.noCourse.toString()){
                startActivity(context, Intent(context, ClipViewerActivity::class.java), null)
            }
            App.binder.getClipRepleList(item.noContent.toString(), "0", "1") {

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class HolderClipSet(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val ivContent: ImageView = itemView.findViewById(R.id.ivContent)
        val view: View = itemView

    }
}
