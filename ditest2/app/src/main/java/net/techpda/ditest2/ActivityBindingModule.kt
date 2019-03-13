package net.techpda.ditest2

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

//    @ContributesAndroidInjector(modules = arrayOf(MainFragmentBindingModule::class))
    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

}