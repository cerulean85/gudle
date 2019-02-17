package net.techpda.gudle

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CategoryFragment : Fragment() {

    private var rv: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {

        rv = inflater.inflate(R.layout.recycler_view_tab_home, container, false) as RecyclerView
        rv!!.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(this.context).orientation))


        var noCategory = arguments!!.getInt("no_category")
        if (noCategory > 0) {
            App.binder.getCourseCollectionClassfiedByCategory(noCategory.toString(), {
                rv!!.adapter = HomeContentsListAdapter(context!!, JCModel.collectionCoruseClassifiedByCategory[noCategory.toString()]!!)
            })
        } else {
            rv!!.adapter = HomeContentsListAdapter(context!!, JCModel.main.collectionCourse!!)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                rv!!.addOnScrollListener(object: RecyclerView.OnScrollListener() {

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                        val firstItemIndex:Int = (rv!!.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        if(firstItemIndex == 0)
                        {
                            ViewBehavior.yPosTabHomeBanner = resources.getDimension(R.dimen.top_view_pager_height) * (if(dy < 0) 0F else -1F)

                            val movingAllowed: Boolean = if(dy==0) false else true
                            if(movingAllowed)
                                ViewBehavior.moveTabHomeBannerTo()
                        }


                        val lastItemIndex:Int = (rv!!.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                        if(lastItemIndex == (JCModel.main.collectionCourse!!.count()-1)) {
                            println("Get Item.")
                        }
                    }
                })
            }
        }
        return rv
    }

    companion object {

        // Method for creating new instances of the fragment
        fun newInstance(category: Category): CategoryFragment {

            // Store the movie data in a Bundle object
            val args = Bundle()
            val noCategory: Int = category.noCategory.toInt()
            args.putInt("전체", noCategory)
            args.putString("no_category", category.title)

//            args.putString(MovieHelper.KEY_TITLE, album.title)
//            args.putInt(MovieHelper.KEY_RATING, album.year)
//            args.putString(MovieHelper.KEY_POSTER_URI, album.image)
//            args.putString(MovieHelper.KEY_OVERVIEW, album.title)

            // Create a new CategoryFragment and set the Bundle as the arguments
            // to be retrieved and displayed when the view is created
            val fragment = CategoryFragment()
            fragment.arguments = args
            return fragment
        }
    }
}