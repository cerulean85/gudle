package net.techpda.gudle

import android.graphics.Color
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

        viewPager = homeView!!.tabHomeContentsFrameViewPager
        tabLayout = homeView!!.recyclerTabLayout
        marketingPaper = homeView!!.tabHomeMarketingFrameViewPager

        App.binder.getMain(Status.CALL_PAGE_FIRST.value, "", {

//
//            JCModel.m = {
//////                val height = resources.getDimension(R.dimen.top_view_pager_height)
////                homeView!!.animate().translationY(JCModel.scrollY).withLayer()
////            }


            ViewBehavior.heightMainActivity = ViewBehavior.heightInitMainActivity
            ViewBehavior.adjustMainActivityHeight()


            ViewBehavior.moveTabHomeBannerTo = {
                homeView!!.animate().translationY(ViewBehavior.yPosTabHomeBanner).withLayer()
            }
            JCModel.onScrolledHomeChanged = { oldValue, newValue ->

                val height = resources.getDimension(R.dimen.top_view_pager_height)
//                if(newValue) {
//                    homeView!!.animate().translationY(-height).withLayer()
//                }
//                else {
//                    val currentY: Float = homeView!!.y
//                    if(currentY < 0)
//                        homeView!!.animate().translationY(height).withLayer()
//                }

//                if(!oldValue && newValue) {
//                    homeView!!.animate().translationY(-height).withLayer()
//                } else {
////                    homeView!!.animate().translationY(0F).withLayer()
//                }

            }
//            val height = resources.getDimension(R.dimen.top_view_pager_height)

//            homeView!!.animate().translationY(-height).withLayer()

            val childCount: Int = JCModel.main.collectionBanner!!.count()
            for (i in 0 until childCount)
            {

                var circle = View(this.context)
                var drawableBG: Int = R.drawable.view_style_circle_tab_home_banner_released
                circle.layoutParams = ViewGroup.LayoutParams(Util.dp(8), Util.dp(8))
                if(marketingPaper!!.currentItem == i)
                    drawableBG = R.drawable.view_style_circle_tab_home_banner_selected

                circle.setBackgroundResource(drawableBG)
                circle.id = i
                homeView!!.tabHomeIndicatorBannerLayout.addView(circle)

                if(i != childCount - 1) {
                    var interval = View(this.context)
                    interval.id = i*10
                    interval.layoutParams = ViewGroup.LayoutParams(Util.dp(4), Util.dp(1))
                    interval.setBackgroundColor(Color.TRANSPARENT)
                    homeView!!.tabHomeIndicatorBannerLayout.addView(interval)

                }
            }



            viewPager!!.adapter = categoryPagerAdapter
            tabLayout!!.setUpWithViewPager(viewPager!!)
            marketingPaper!!.adapter = HomeBannerListAdapter(context!!, JCModel.main.collectionBanner!!)
//            marketingPaper!!.post {
//                marketingPaper!!.currentItem = 1
//            }

            timer = Timer().schedule(3000, 3000) {

                val indexPrev: Int = marketingPaper!!.currentItem
//                var indexCurrent: Int = marketingPaper!!.currentItem
                val indexLast: Int = JCModel.main.collectionBanner!!.size - 1
                val indexCurrent = if(indexPrev == indexLast) 0 else (indexPrev+1)
                marketingPaper!!.setCurrentItem(indexCurrent, true)

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

                val indexPrev: Int = marketingPaper!!.currentItem
                val indexLast: Int = JCModel.main.collectionBanner!!.size - 1
                val indexCurrent = if(indexPrev == indexLast) 0 else (indexPrev+1)


                var circle = homeView!!.tabHomeIndicatorBannerLayout.findViewById<View>(indexPrev)
                var drawableBG: Int = R.drawable.view_style_circle_tab_home_banner_released
                circle.setBackgroundResource(drawableBG)


                circle = homeView!!.tabHomeIndicatorBannerLayout.findViewById<View>(indexCurrent)
                drawableBG = R.drawable.view_style_circle_tab_home_banner_selected
                circle.setBackgroundResource(drawableBG)

            }
            override fun onPageSelected(position: Int) {
                print("onPageSelected")
            }
        })

        return homeView!!
    }

}
