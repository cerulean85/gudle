package net.techpda.myapplication

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    internal abstract fun provideListFragment(): ListFragment
//
//    @ContributesAndroidInjector
//    internal abstract fun provideDetailsFragment(): DetailsFragment
}
