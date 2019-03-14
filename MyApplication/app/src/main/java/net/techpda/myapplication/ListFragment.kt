package net.techpda.myapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import javax.inject.Inject

class ListFragment : BaseFragment(), RepoSelectedListener {

    @BindView(R.id.recyclerView)
    internal var listView: RecyclerView? = null
    @BindView(R.id.tv_error)
    internal var errorTextView: TextView? = null
    @BindView(R.id.loading_view)
    internal var loadingView: View? = null

    @Inject
    internal var viewModelFactory: ViewModelFactory? = null
    private var viewModel: ListViewModel? = null

    override fun layoutRes(): Int {
        return R.layout.screen_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)

        listView!!.addItemDecoration(DividerItemDecoration(baseActivity!!, DividerItemDecoration.VERTICAL))
        listView!!.adapter = RepoListAdapter(viewModel!!, this, this)
        listView!!.layoutManager = LinearLayoutManager(context)

        observableViewModel()
    }

    override fun onRepoSelected(repo: Repository) {
//        val detailsViewModel = ViewModelProviders.of(baseActivity!!, viewModelFactory).get(DetailsViewModel::class.java)
//        detailsViewModel.setSelectedRepo(repo)
//        baseActivity!!.supportFragmentManager.beginTransaction().replace(R.id.screenContainer, DetailsFragment())
//                .addToBackStack(null).commit()
    }

    private fun observableViewModel() {
        viewModel!!.repos.observe(this, Observer { repos -> if (repos != null) listView!!.visibility = View.VISIBLE })

        viewModel!!.error.observe(this, Observer { isError ->
            if (isError != null)
                if (isError!!) {
                    errorTextView!!.visibility = View.VISIBLE
                    listView!!.visibility = View.GONE
                    errorTextView!!.text = "An Error Occurred While Loading Data!"
                } else {
                    errorTextView!!.visibility = View.GONE
                    errorTextView!!.text = null
                }
        })

        viewModel!!.loading.observe(this, Observer  { isLoading ->
            if (isLoading != null) {
                loadingView!!.visibility = if (isLoading) View.VISIBLE else View.GONE
                if (isLoading!!) {
                    errorTextView!!.visibility = View.GONE
                    listView!!.visibility = View.GONE
                }
            }
        })
    }
}
