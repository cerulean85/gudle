package net.techpda.ditest2

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ContextModule::class, AndroidSupportInjectionModule::class, ActivityBindingModule::class))
interface ApplicationComponent {
    fun inject(application: BaseApplication)
}

@Component.Builder
interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): ApplicationComponent
}