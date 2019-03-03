package techpda.net.cliplearning.views

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import techpda.net.cliplearning.R
import techpda.net.cliplearning.databinding.ActivitySplashBinding
import techpda.net.cliplearning.viewmodels.SplashViewModel
import techpda.net.cliplearning.viewmodels.ViewModelKey


@Module
internal abstract class SplashActivityModule {
    @ContributesAndroidInjector()
    internal abstract fun splashActivity(): SplashActivity

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashwViewModel(viewModel: SplashViewModel): ViewModel
}

class SplashActivity : BaseDaggerAppCompatActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idLayout = R.layout.activity_splash
        setContentView(idLayout)
        setViewModel(SplashViewModel::class.java)

        binding.viewModel = viewModel
        binding.executePendingBindings()

        viewModel.intent(MainActivity::class.java)

    }

}
