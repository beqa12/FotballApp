package com.example.footballapp.ui.details.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.data.repository.FootballRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchDetailViewModel(private val repoImpl: FootballRepoImpl): ViewModel() {


    fun fetchData(){
        try {
            viewModelScope.launch {
                val response = repoImpl.getInfo()
                withContext(Dispatchers.Main){
                    Log.e("TAG", response.match.stadiumAdress.toString())
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }

    }
}