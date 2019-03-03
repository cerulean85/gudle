package techpda.net.cliplearning.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.multibindings.IntoMap
import techpda.net.cliplearning.R
import techpda.net.cliplearning.models.MainModel
import techpda.net.cliplearning.viewmodels.MainViewModel
import techpda.net.cliplearning.databinding.ActivityMainBinding
import techpda.net.cliplearning.viewmodels.OverviewViewModel
import techpda.net.cliplearning.viewmodels.ViewModelKey
import javax.inject.Inject


@Module
internal abstract class MainActivityModule {

    @ContributesAndroidInjector()
    internal abstract fun mainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}

class MainActivity : BaseDaggerAppCompatActivity<ActivityMainBinding, MainViewModel>(), RepositoryRecyclerViewAdapter.OnItemClickListener {

    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)

    override fun onItemClick(position: Int) {
        println("hahahahah")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idLayout = R.layout.activity_main
        setContentView(idLayout)
        setViewModel(MainViewModel::class.java)

        binding.vm = viewModel
        binding.executePendingBindings()

        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter
        viewModel.repository.observe(this,
            Observer<ArrayList<MainModel>> {
                it?.let{ repositoryRecyclerViewAdapter.replaceData(it)} })

//        startActivity(Intent(this, OverviewActivity::class.java))
    }
}
