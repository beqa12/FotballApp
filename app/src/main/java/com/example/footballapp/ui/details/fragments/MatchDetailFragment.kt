package com.example.footballapp.ui.details.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.footballapp.R
import com.example.footballapp.databinding.MatchDetailFragmentLayoutBinding
import com.example.footballapp.domain.resource.Status
import com.example.footballapp.ui.base.BaseFragment
import com.example.footballapp.ui.details.viewmodel.MatchDetailViewModel
import com.example.footballapp.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchDetailFragment : BaseFragment<MatchDetailFragmentLayoutBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MatchDetailFragmentLayoutBinding
        get() = MatchDetailFragmentLayoutBinding::inflate

    private val matchDetailViewmodel: MatchDetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
        fetchData()
    }

    private fun initActions(){
        binding.backArrow.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun fetchData(){
        matchDetailViewmodel.fetchDataMatchDetails()
        observe()
    }

    private fun observe(){
        matchDetailViewmodel.matchDetails.observe(viewLifecycleOwner){
            when(it){
                is Status.SUCCESS -> {
                    binding.teamsDetailCustomView.setData(it.data)
//                    Log.e("TAG", "MatchInfo -> ${it.data.match}")
                }
                is Status.ERROR -> {
                    requireContext().showToast(it.errorMessage)
                }
                is Status.NO_INTERNET -> {
                    requireContext().showToast(requireContext().getString(R.string.internet_problem))
                }
                else -> println("Error occurred")
            }
        }
        matchDetailViewmodel.progressBar.observe(viewLifecycleOwner){
            binding.progressBar.isVisible = it
        }
    }
}