package net.techpda.myapplication

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import java.util.ArrayList

class RepoListAdapter internal constructor(viewModel: ListViewModel, lifecycleOwner: LifecycleOwner, private val repoSelectedListener: RepoSelectedListener) : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {
    private val data = ArrayList<Repository>()

    init {
        viewModel.getRepos().observe(lifecycleOwner, Observer { repos ->
            data.clear()
            if (repos != null) {
                data.addAll(repos)
                notifyDataSetChanged()
            }
        })
        setHasStableIds(true)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_repo_list_item, parent, false)
        return RepoViewHolder(view, repoSelectedListener)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data[position].id
    }

    class RepoViewHolder(itemView: View, repoSelectedListener: RepoSelectedListener) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tv_repo_name)
        var repoNameTextView: TextView? = null
        @BindView(R.id.tv_repo_description)
        var repoDescriptionTextView: TextView? = null
        @BindView(R.id.tv_forks)
        var forksTextView: TextView? = null
        @BindView(R.id.tv_stars)
        var starsTextView: TextView? = null

        private var repo: Repository? = null

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener { v ->
                if (repo != null) {
                    repoSelectedListener.onRepoSelected(repo!!)
                }
            }
        }

        fun bind(repo: Repository) {
            this.repo = repo
            repoNameTextView!!.text = repo.name
            repoDescriptionTextView!!.text = repo.description
            forksTextView!!.text = repo.forks.toString()
            starsTextView!!.text = repo.stars.toString()
        }
    }
}
