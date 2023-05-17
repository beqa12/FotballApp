package com.example.footballapp.ui.details.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.footballapp.databinding.MatchDetailFragmentLayoutBinding
import com.example.footballapp.ui.base.BaseFragment
import com.example.footballapp.ui.details.viewmodel.MatchDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchDetailFragment : BaseFragment<MatchDetailFragmentLayoutBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MatchDetailFragmentLayoutBinding
        get() = MatchDetailFragmentLayoutBinding::inflate

    private val matchDetailViewmodel: MatchDetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        matchDetailViewmodel.fetchData()
    }
}