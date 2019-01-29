package net.techpda.gudle

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class CategoryPagerAdapter(fragmentManager: FragmentManager, private val albums: ArrayList<Category>) :
        FragmentStatePagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        return CategoryFragment.newInstance(albums[position]) // (movies[position])
    }

    // 3
    override fun getCount(): Int {
        return albums.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return albums[position % albums.size].title?: "untitled"
    }
}