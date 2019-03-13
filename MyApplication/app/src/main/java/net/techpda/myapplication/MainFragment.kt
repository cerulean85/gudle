package net.techpda.myapplication

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import net.techpda.myapplication.databinding.MainFragmentBinding
import javax.inject.Inject


class MainFragment @Inject constructor() : DaggerFragment() {
    @Inject
    lateinit var binding: MainFragmentBinding

    @Inject
    @field:Named("hello")
    lateinit var txtHelloWorld: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = txtHelloWorld
    }
}