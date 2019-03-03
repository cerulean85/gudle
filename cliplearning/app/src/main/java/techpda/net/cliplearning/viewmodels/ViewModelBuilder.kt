package techpda.net.cliplearning.viewmodels

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import techpda.net.cliplearning.DaggerAwareViewModelFactory

@Module
internal abstract class ViewModelBuilder {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerAwareViewModelFactory):
            ViewModelProvider.Factory
}