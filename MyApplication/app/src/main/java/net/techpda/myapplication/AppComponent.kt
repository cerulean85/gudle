package net.techpda.myapplication

import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component (modules = [
    (AndroidSupportInjectionModule::class),
    (NetworkModule::class),
    (ActivityModule::class),
    (ContextModule::class),
    (ActivityBindingModule::class),
    (AppModule::class)
])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<App>()

}