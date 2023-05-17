package com.example.footballapp.ui.details.activity

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.footballapp.R
import com.example.footballapp.databinding.MatchDetailActivityLayoutBinding
import com.example.footballapp.ui.base.BaseActivity
import com.example.footballapp.ui.details.factory.MatchFragmentFactory
import com.example.footballapp.ui.details.fragments.MatchDetailFragment
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView

class MatchDetailActivity : BaseActivity<MatchDetailActivityLayoutBinding>() {


    override fun getViewBinding(): MatchDetailActivityLayoutBinding = MatchDetailActivityLayoutBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MatchFragmentFactory()
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        addBadge()
        if(supportFragmentManager.fragments.size == 0){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MatchDetailFragment::class.java, null)
                .commit()
        }
    }

    private fun addBadge() {
        val bottomMenu = binding.bottomNav.getChildAt(0) as? BottomNavigationMenuView
        val v = bottomMenu?.getChildAt(3) as? BottomNavigationItemView
        val badge = LayoutInflater.from(this)
            .inflate(R.layout.badge_layout, bottomMenu, false)
        val badgeLayout: FrameLayout.LayoutParams = FrameLayout.LayoutParams(badge.layoutParams).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            topMargin = 14
            leftMargin = 26
        }
        v?.addView(badge, badgeLayout)
    }
}