package net.techpda.mvvmtestagain

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

//    lateinit var binding: ActivityMainBinding

    var mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.viewModel = mainViewModel
//        binding.executePendingBindings()

    }
}
