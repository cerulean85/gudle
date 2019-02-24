package net.techpda.mvvmtest3

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelBuilder::class,
        MainActivityModule::class])

interface AppComponent: AndroidInjector<ModernApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<ModernApplication>()
}

