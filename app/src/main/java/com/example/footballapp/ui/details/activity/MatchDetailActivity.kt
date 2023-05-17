package com.example.footballapp.ui.details.activity

import android.os.Bundle
import com.example.footballapp.R
import com.example.footballapp.databinding.MatchDetailActivityLayoutBinding
import com.example.footballapp.ui.base.BaseActivity
import com.example.footballapp.ui.details.factory.MatchFragmentFactory
import com.example.footballapp.ui.details.fragments.MatchDetailFragment

class MatchDetailActivity : BaseActivity<MatchDetailActivityLayoutBinding>() {


    override fun getViewBinding(): MatchDetailActivityLayoutBinding = MatchDetailActivityLayoutBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MatchFragmentFactory()
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        if(supportFragmentManager.fragments.size == 0){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MatchDetailFragment::class.java, null)
                .commit()
        }
    }
}