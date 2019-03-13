package net.techpda.myapplication

import android.databinding.DataBindingUtil
import dagger.Module
import dagger.Provides
import net.techpda.myapplication.databinding.MainFragmentBinding

@Module
class MainFragmentModule {
    @Provides
    @FragmentScope
    fun provideMainFragmentBinding(activity: MainActivity): MainFragmentBinding {
        return DataBindingUtil.inflate<MainFragmentBinding>(
                activity.layoutInflater,
                R.layout.main_fragment,
                null,
                false
        )
    }
}