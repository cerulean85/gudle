package net.techpda.myapplication

import android.databinding.DataBindingUtil
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import net.techpda.myapplication.databinding.MainActivityBinding

@Module
abstract class MainActivityModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideMainActivityBinding(activity: MainActivity): MainActivityBinding {
            return DataBindingUtil.setContentView(activity, R.layout.main_activity)
        }
    }

    @FragmentScope
    @ContributesAndroidInjector(modules = [(MainFragmentModule::class)])
    abstract fun getMainFragment(): MainFragment

}