package net.techpda.gudle.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tab_more.view.*
import net.techpda.gudle.R
import net.techpda.gudle.Util

class TabMore : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var v: View? = inflater.inflate(R.layout.fragment_tab_more, container, false)



//        v!!.moreScv.layoutParams.height = Util.heightDisplay - Util.heightStatusBar - 400

        v!!.testBtn.setOnClickListener {


        }
        v!!.requestLayout()


        return v
    }
}
