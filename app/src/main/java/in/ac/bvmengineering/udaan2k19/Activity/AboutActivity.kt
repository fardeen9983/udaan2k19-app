package `in`.ac.bvmengineering.udaan2k19.Activity

import `in`.ac.bvmengineering.udaan2k19.Fragment.FragmentAbout
import `in`.ac.bvmengineering.udaan2k19.Fragment.FragmentDeveloper
import `in`.ac.bvmengineering.udaan2k19.R
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about.*


class AboutActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        collaps_toolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collaps_toolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        setSupportActionBar(toolbar)
        nested_scroll.isFillViewport = true

        val mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }

    class SectionsPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int) =
                when (position) {
                    0 -> FragmentDeveloper()
                    1 -> FragmentAbout()
                    else -> FragmentDeveloper()
                }

        override fun getCount() = 2
    }
}
