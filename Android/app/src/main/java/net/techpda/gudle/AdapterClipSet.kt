package net.techpda.gudle

import android.content.Context
import android.content.Intent
import android.databinding.ViewDataBinding
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import net.techpda.gudle.activities.ClipViewerActivity
import android.databinding.DataBindingUtil
import net.techpda.gudle.databinding.ItemClipSetBinding


class AdapterClipSet: RecyclerView.Adapter<AdapterClipSet.HolderClipSet<ItemClipSetBinding>>() {

    var items:ArrayList<CourseListItemViewModel> = ArrayList()

    fun add(items: ArrayList<CourseListItemViewModel>) {

        for (item in items) {
            if (!this.items.contains(item)) {
                this.items.add(item)
                notifyItemInserted(this.itemCount - 1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderClipSet<ItemClipSetBinding> {

        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_clip_set, parent,false)

        return HolderClipSet(v)
    }


    override fun onBindViewHolder(holder: HolderClipSet<ItemClipSetBinding>, position: Int) {

        holder.binding().item = items[position]

//        val item = list[position]
//        holder.tvTitle.text = item.title
//
//        Picasso.get().load(item.urlImage01).into(holder.ivContent)
//
//        holder.view.setOnClickListener {
//
//            App.binder.getClipDetail(item.noContent.toString(), item.noCourse.toString()){
//                startActivity(context, Intent(context, ClipViewerActivity::class.java), null)
//            }
//            App.binder.getClipRepleList(item.noContent.toString(), "0", "1") {
//
//            }
//        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

//    inner class HolderClipSet(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
//        val ivContent: ImageView = itemView.findViewById(R.id.ivContent)
//        val view: View = itemView
//
//    }

    inner class HolderClipSet<T : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: T

        init {
            this.binding = DataBindingUtil.bind<ViewDataBinding>(itemView) as T
        }

        fun binding(): T {
            return binding
        }
    }
}
