package techpda.net.cliplearning.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import techpda.net.cliplearning.DaggerAwareViewModelFactory
import javax.inject.Singleton

@Module
abstract class ViewModelBuilder {

//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel::class)
//    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(OverviewViewModel::class)
//    abstract fun bindOverviewViewModel(overviewViewModel: OverviewViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(OverviewViewModel::class)
//    abstract fun bindOverviewViewModel(overviewViewModel: OverviewViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerAwareViewModelFactory):
            ViewModelProvider.Factory
}