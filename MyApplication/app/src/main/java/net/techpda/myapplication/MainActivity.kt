package net.techpda.myapplication

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import dagger.android.support.DaggerAppCompatActivity

import kotlinx.android.synthetic.main.main_activity.*
import net.techpda.myapplication.databinding.MainActivityBinding
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    @Inject
    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commitNow()

        supportFragmentManager.beginTransaction().add(R.id.screenContainer, ListFragment()).commit()
    }

}
