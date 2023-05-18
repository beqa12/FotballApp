package com.example.footballapp.ui.details.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.data.repository.MatchDetailRepoImpl
import com.example.footballapp.domain.models.MatchDetail
import com.example.footballapp.domain.resource.Status
import com.example.footballapp.domain.usecase.MatchDetailsUseCase
import com.example.footballapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchDetailViewModel(private val matchDetailUseCase: MatchDetailsUseCase) : BaseViewModel() {

    private var _matchDetails = MutableLiveData<Status<MatchDetail>>()
    val matchDetails: LiveData<Status<MatchDetail>> get() = _matchDetails

    fun fetchDataMatchDetails() {
        viewModelScope.launch {
            try {
                val response = matchDetailUseCase.execute()
                withContext(Dispatchers.Main) {
                    _matchDetails.value = Status.SUCCESS(response, "Success")
                }
            } catch (e: Exception) {
                handleExceptions(e, _matchDetails)
                e.printStackTrace()
            }
        }

    }
}