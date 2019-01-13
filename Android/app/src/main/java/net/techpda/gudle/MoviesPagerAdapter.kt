package net.techpda.gudle

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MoviesPagerAdapter(fragmentManager: FragmentManager, private val albums: ArrayList<Album>) :
        FragmentStatePagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        return MovieFragment.newInstance(albums[position]) // (movies[position])
    }

    // 3
    override fun getCount(): Int {
        return albums.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return albums[position % albums.size].title
    }
}