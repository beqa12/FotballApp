package com.example.footballapp.ui.details.factory

import androidx.fragment.app.FragmentFactory
import com.example.footballapp.ui.details.fragments.MatchDetailFragment

class MatchFragmentFactory: FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when(className){
            MatchDetailFragment::class.java.name -> {
                MatchDetailFragment()
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }

}