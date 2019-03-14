package techpda.net.cliplearning.viewmodels

import dagger.Component
import techpda.net.cliplearning.networks.NetworkModule
import javax.inject.Singleton

@Singleton
@Component
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param mainViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
    }
}