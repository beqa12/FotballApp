package com.example.footballapp.ui.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballapp.domain.resource.Status
import com.example.footballapp.utils.ApiExceptions
import com.example.footballapp.utils.NoInternetException
import com.example.footballapp.utils.SERVER_ERROR
import com.example.footballapp.utils.UNKNOWN_ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

open class BaseViewModel : ViewModel(){


    var progressBar = MutableLiveData<Boolean>()

    fun showLoader() {
        progressBar.postValue(true)
    }

    fun hideLoader() {
        progressBar.postValue(false)
    }

    suspend fun <T> handleExceptions(exception: Exception, data: MutableLiveData<Status<T>>) {
        when (exception) {
            is NoInternetException -> {
                withContext(Dispatchers.Main) {
                    data.postValue(Status.NO_INTERNET)
                }
            }
            is ApiExceptions -> {
                when (exception.message) {
                    SERVER_ERROR -> {
                        data.postValue(Status.ERROR(errorMessage = SERVER_ERROR))
                    }
                    else -> {
                        data.postValue(Status.ERROR(errorMessage = UNKNOWN_ERROR))
                    }
                }
            }
            else -> {
                withContext(Dispatchers.Main) {
                    data.postValue(Status.ERROR(errorMessage = UNKNOWN_ERROR))
                }
            }
        }

    }

}