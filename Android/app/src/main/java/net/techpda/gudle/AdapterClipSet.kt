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


class AdapterClipSet(private val items: ArrayList<Clip>) : RecyclerView.Adapter<AdapterClipSet.BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {

        val binding = DataBindingUtil.inflate<ItemClipSetBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_clip_set, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {

        var binding = holder.binding
        binding.model = CourseListItemViewModel(items[position])
        Picasso.get().load(items[position].urlImage01).into(binding.ivContent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class BindingHolder(val binding: ItemClipSetBinding) : RecyclerView.ViewHolder(binding.mainLayout)

}
