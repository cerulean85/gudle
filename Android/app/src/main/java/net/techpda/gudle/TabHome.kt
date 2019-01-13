package net.techpda.gudle

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tab_home.*
import kotlinx.android.synthetic.main.fragment_tab_home.view.*
import org.jetbrains.anko.support.v4.viewPager


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TabHome.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TabHome.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TabHome : Fragment() {



    private lateinit var pagerAdapter: MoviesPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.binder.getDummyAll { setViewPagerAdapter() }
        App.binder.getMarketSet()
    }


    private fun setViewPagerAdapter()
    {
        viewPager.adapter = pagerAdapter
        recyclerTabLayout.setUpWithViewPager(viewPager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment



        var view: View = inflater.inflate(R.layout.fragment_tab_home, container, false)

        var list: ArrayList<Album> = JCModel.dataHome.course
        pagerAdapter = MoviesPagerAdapter(fragmentManager!!, list)
        view.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                print("onPageScrollStateChanged")
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                print("onPageScrolled")
            }
            override fun onPageSelected(position: Int) {
                print("onPageSelected")
            }
        })

        view.marketingPager.adapter = CustomPagerAdapter(context!!, JCModel.marketSet!!.list)
        view.marketingPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                print("onPageScrollStateChanged")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                print("onPageScrolled")
            }
            override fun onPageSelected(position: Int) {
                print("onPageSelected")
            }
        })

        return view
    }

}
