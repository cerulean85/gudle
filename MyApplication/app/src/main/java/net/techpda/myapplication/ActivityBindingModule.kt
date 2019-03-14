package net.techpda.myapplication

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = arrayOf(MainFragmentBindingModule::class))
    internal abstract fun bindMainActivity(): MainActivity
}
