package com.example.mvvm_project.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mvvm_project.models.UserDetails
import com.example.mvvm_project.network.UsersApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    var usersLiveData: LiveData<PagedList<UserDetails>>

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _property = MutableLiveData<UserDetails>()
    val property : LiveData<UserDetails>
    get() = _property

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getUserProperties()
        val config = PagedList.Config.Builder()
            .setPageSize(100)
            .setEnablePlaceholders(false)
            .build()
        usersLiveData =
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getUserProperties() {
        coroutineScope.launch {
            var getPropertiesDeferred = UsersApi.retrofitService.getProperties(5)

            try {
                var listResult = getPropertiesDeferred.await()
                _status.value = "Success: ${listResult.size} User properties retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun display(list: UserDetails) {
        _property.value = list
    }

    fun getItems() : LiveData<PagedList<UserDetails>> = usersLiveData
}
