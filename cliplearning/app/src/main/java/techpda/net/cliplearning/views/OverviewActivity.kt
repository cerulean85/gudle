package techpda.net.cliplearning.views

import android.os.Bundle
import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import techpda.net.cliplearning.R
import techpda.net.cliplearning.databinding.ActivityOverviewBinding
import techpda.net.cliplearning.viewmodels.OverviewViewModel
import techpda.net.cliplearning.viewmodels.ViewModelKey

@Module
internal abstract class OverviewActivityModule {
    @ContributesAndroidInjector()
    internal abstract fun overviewActivity(): OverviewActivity

    @Binds
    @IntoMap
    @ViewModelKey(OverviewViewModel::class)
    abstract fun bindOverviewViewModel(viewModel: OverviewViewModel): ViewModel
}

class OverviewActivity: BaseDaggerAppCompatActivity<ActivityOverviewBinding, OverviewViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idLayout = R.layout.activity_overview
                setContentView(idLayout)
        setViewModel(OverviewViewModel::class.java)

        binding.viewModel = viewModel
        binding.executePendingBindings()



    }
}