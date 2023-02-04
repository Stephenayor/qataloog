package com.example.qataloog.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return RecommendedBooksFragment()
            }
            1 -> {
                return RecommendedBooksFragment()
            }
            else -> {
                return RecommendedBooksFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "RECOMMENDED"
            }
            1 -> {
                return "CATEGORY B"
            }
            2 -> {
                return "CATEGORY C"
            }
        }
        return super.getPageTitle(position)
    }
}