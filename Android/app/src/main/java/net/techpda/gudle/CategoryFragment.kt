package net.techpda.gudle

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx

class CategoryFragment : Fragment() {



    private lateinit var view: AnkoContext<Fragment>
    private var rv: RecyclerView? = null
    private var currentScrolledPosition = 0
    private var scrolled: Boolean = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {


        view = UI {
            rv = recyclerView { }
            rv!!.layoutManager = LinearLayoutManager(this.ctx)
            rv!!.addItemDecoration(DividerItemDecoration(this.ctx, LinearLayoutManager(this.ctx).orientation))
        }


        var noCategory = arguments!!.getInt("no_category")
        if (noCategory > 0) {

            App.binder.getCourseCollectionClassfiedByCategory(noCategory.toString(), {
                rv!!.adapter = HomeContentsListAdapter(this.ctx, JCModel.collectionCoruseClassifiedByCategory[noCategory.toString()]!!)
            })


//            App.binder.getCourseByCategory(noCategory.toString()) {
//
//                rv!!.adapter = HomeContentsListAdapter(this.ctx, JCModel.mapCourse[noCategory.toString()]!!)
//            }
        } else {
            rv!!.adapter = HomeContentsListAdapter(this.ctx, JCModel.main.collectionCourse!!)


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                rv!!.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener{
                    override fun onInterceptTouchEvent(p0: RecyclerView, p1: MotionEvent): Boolean {

                        return false
                    }

                    override fun onRequestDisallowInterceptTouchEvent(p0: Boolean) {

                    }

                    override fun onTouchEvent(p0: RecyclerView, p1: MotionEvent) {
                        println("touched Event")
                    }

                })
                rv!!.addOnScrollListener(object: RecyclerView.OnScrollListener() {



                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                        println("scrollDy: $dy" )
                        currentScrolledPosition += dy
                        println("scrolled dY: $currentScrolledPosition")
                        if(currentScrolledPosition==0 && scrolled) {
                            JCModel.scrolledHome = true
                        }
                        scrolled = true
                    }

                    override fun onScrollStateChanged(view: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(view, newState)

//                        scrolled = true
//                        println("scrolled: $scrolledState")

//var ccc = view.layoutManager!!.itemCount
//                        var dd = view.layoutManager!!.off
//                        println("scrollDy: $dd" )
                    }


                })


//                rv!!.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//
//                    var xxx = v.y
//
//                    println("v.x: $xxx, oldScrollY: $oldScrollY, scrollY : $scrollY" )
//                    if(oldScrollY == 0 && scrollY > 0) {
//                        JCModel.scrolledHome = true
//                    }
//                }



            }
        }

        return view.view
/*        return UI {
            rv = recyclerView { }

            rv!!.layoutManager = LinearLayoutManager(this.ctx)
            rv!!.addItemDecoration(DividerItemDecoration(this.ctx, LinearLayoutManager(this.ctx).orientation))
//            rv.adapter = HomeContentsListAdapter(this.ctx, JCModel.dummySet!!.list, JCModel.imageSet!!.list)
//            rv.adapter = HomeContentsListAdapter(this.ctx, JCModel.dataHome.course)

        }.view*/


        // Creates the view controlled by the fragment
//        val view = inflater.inflate(R.layout.fragment_movie, container, false)
//        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
//        val ratingTextView = view.findViewById<TextView>(R.id.ratingTextView)
//        val posterImageView = view.findViewById<ImageView>(R.id.posterImageView)
//        val overviewTextView = view.findViewById<TextView>(R.id.overviewTextView)
//
//        // Retrieve and display the movie data from the Bundle
//        val args = arguments
//        titleTextView.text = args?.getString(MovieHelper.KEY_TITLE)
//        ratingTextView.text = String.format("%d/10", args?.getInt(MovieHelper.KEY_RATING))
//        overviewTextView.text = args?.getString(MovieHelper.KEY_OVERVIEW)
//
//        // Download the image and display it using Picasso
//        Picasso.get()
//                .load(args?.getString(MovieHelper.KEY_POSTER_URI))
//                .placeholder(R.drawable.p1)
//                .error(R.drawable.ic_launcher_background)
//                .into(posterImageView)

//        return view
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