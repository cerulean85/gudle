package techpda.net.cliplearning

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import techpda.net.cliplearning.networks.NetworkModule
import techpda.net.cliplearning.viewmodels.ViewModelBuilder
import techpda.net.cliplearning.views.OverviewActivityModule
import techpda.net.cliplearning.views.MainActivityModule
import techpda.net.cliplearning.views.SplashActivityModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelBuilder::class,
        OverviewActivityModule::class,
        MainActivityModule::class,
        SplashActivityModule::class
    ])

interface AppComponent: AndroidInjector<ModernApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<ModernApplication>()

}