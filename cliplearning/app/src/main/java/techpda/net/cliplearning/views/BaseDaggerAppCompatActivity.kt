package techpda.net.cliplearning.views

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.NonNull
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseDaggerAppCompatActivity<T1: ViewDataBinding, T2: ViewModel>: DaggerAppCompatActivity() {

    var idLayout: Int = 0
    lateinit var binding: T1
    lateinit var viewModel: T2

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    fun setViewModel(@NonNull cls: Class<T2>) {
        binding = DataBindingUtil.setContentView(this, idLayout)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(cls)
    }

}