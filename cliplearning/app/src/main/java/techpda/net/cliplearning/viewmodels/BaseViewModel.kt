package techpda.net.cliplearning.viewmodels

import android.arch.lifecycle.ViewModel
import android.content.Intent
import techpda.net.cliplearning.networks.NetworkModule
import techpda.net.cliplearning.repositories.Repository


abstract class BaseViewModel(var _repo: Repository): ViewModel()  {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when(this) {
            is MainViewModel -> injector.inject(this)
        }
    }

    fun intent(cls: Class<*>) {
        cls.let {
            val context = _repo.getContext()
            context.startActivity(Intent(context, cls))
        }
    }
}