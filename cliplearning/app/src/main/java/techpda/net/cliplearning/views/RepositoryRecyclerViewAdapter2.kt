package techpda.net.cliplearning.views

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import techpda.net.cliplearning.databinding.RvItemRepository2Binding
import techpda.net.cliplearning.models.MainModel

class RepositoryRecyclerViewAdapter2(private var items: ArrayList<MainModel>,
                                     private var listener: OnItemClickListener
)
    : RecyclerView.Adapter<RepositoryRecyclerViewAdapter2.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RepositoryRecyclerViewAdapter2.ViewHolder {
        val layoutInflater = LayoutInflater.from(p0?.context)
        val binding = RvItemRepository2Binding.inflate(layoutInflater, p0, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) = p0.bind(items[p1], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun replaceData(data: ArrayList<MainModel> ) {
        items = data
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: RvItemRepository2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: MainModel, listener: OnItemClickListener?) {
            binding.repository = repo
            if(listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(layoutPosition) }
            }

            binding.executePendingBindings()
        }
    }
}