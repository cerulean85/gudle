package net.techpda.gudle

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nshmura.recyclertablayout.RecyclerTabLayout
import kotlinx.android.synthetic.main.fragment_tab_home.view.*
import java.util.*
import kotlin.concurrent.schedule

class TabHome : Fragment() {

    private var categoryPagerAdapter: CategoryPagerAdapter? = null
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

    private var homeView: View? = null //inflater!!.inflate(R.layout.fragment_tab_home, container, false)
    private fun createView() : View?
    {


        homeView = inflater!!.inflate(R.layout.fragment_tab_home, container, false)
        if(homeView == null)
            return null

        categoryPagerAdapter = CategoryPagerAdapter(fragmentManager!!, JCModel.systemInfo.collectionCategory!!)

        viewPager = homeView!!.viewPager
        tabLayout = homeView!!.recyclerTabLayout
        marketingPaper = homeView!!.marketingPager

        App.binder.getMain("1", "", {

            JCModel.onScrolledHomeChanged = { oldValue, newValue ->

                if(!oldValue && newValue) {
                    val height = resources.getDimension(R.dimen.top_view_pager_height)
                    homeView!!.animate().translationY(-height).withLayer()
                }

            }

            viewPager!!.adapter = categoryPagerAdapter
            tabLayout!!.setUpWithViewPager(viewPager!!)
            marketingPaper!!.adapter = HomeBannerListAdapter(context!!, JCModel.main.collectionBanner!!)
            marketingPaper!!.post { marketingPaper!!.currentItem = 1 }

            timer = Timer().schedule(3000, 3000) {
//

//                var homeView: View = (inflater!!.inflate(R.layout.fragment_tab_home, container, false))
//                homeView.marketingPager.animate().translationY((-marketingPaper!!.height).toFloat()).withLayer()

//
//                var indexCurrent: Int = marketingPaper!!.currentItem
//                val indexLast: Int = JCModel.dataHome.banner.size - 1
//                indexCurrent = if(indexCurrent == indexLast) 0 else (indexCurrent+1)
//                marketingPaper!!.setCurrentItem(indexCurrent, true)
////                marketingPaper!!.setPageTransformer()
//
            }

        })

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

        return homeView!!
    }

}
