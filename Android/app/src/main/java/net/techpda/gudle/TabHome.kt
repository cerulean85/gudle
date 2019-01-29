package net.techpda.gudle

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nshmura.recyclertablayout.RecyclerTabLayout
import kotlinx.android.synthetic.main.fragment_tab_home.*
import kotlinx.android.synthetic.main.fragment_tab_home.view.*
import org.jetbrains.anko.support.v4.viewPager
import java.util.*
import kotlin.concurrent.schedule

class TabHome : Fragment() {

    private var pagerAdapter: CategoryPagerAdapter? = null
    private var tabLayout: RecyclerTabLayout? = null
    private var viewPager: ViewPager? = null
    private var marketingPaper: ViewPager? = null

    private var container: ViewGroup? = null
    private var inflater: LayoutInflater? = null

    private var viewContens: View? = null

    private var timer: TimerTask? = null

    override fun onCreateView(inf: LayoutInflater, c: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        container = c
        inflater = inf

        if(viewContens == null)
            viewContens = createView()

        return viewContens
    }

    override fun onDestroy() {
        super.onDestroy()

        timer.let { timer!!.cancel() }

    }

    fun createView() : View
    {
        var view: View = inflater!!.inflate(R.layout.fragment_tab_home, container, false)

        pagerAdapter = CategoryPagerAdapter(fragmentManager!!, JCModel.systemInfo.category)

        viewPager = view.viewPager
        tabLayout = view.recyclerTabLayout
        marketingPaper = view.marketingPager

        App.binder.getMain("1", "") {
            viewPager!!.adapter = pagerAdapter
            tabLayout!!.setUpWithViewPager(viewPager!!)
            marketingPaper!!.adapter = CustomPagerAdapter(context!!, JCModel.dataHome.banner)

            marketingPaper!!.post { marketingPaper!!.currentItem = 1 }

            timer = Timer().schedule(3000, 3000) {

                var indexCurrent: Int = marketingPaper!!.currentItem
                val indexLast: Int = JCModel.dataHome.banner.size - 1
                indexCurrent = if(indexCurrent == indexLast) 0 else (indexCurrent+1)
                marketingPaper!!.setCurrentItem(indexCurrent, true)
                marketingPaper!!.setPageTransformer()

            }
        }

        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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

        marketingPaper!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

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
