package com.example.mvvm_project.overview

import android.widget.Toast
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel(), onUserClickListener {

    val usersLiveData = MutableLiveData<List<UserDetails>>()

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _property = MutableLiveData<UserDetails>()
    val property : LiveData<UserDetails>
    get() = _property

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    public fun getUserProperties() {

        coroutineScope.launch {
            var getPropertiesDeferred = UsersApi.retrofitService.getProperties(5)

            try {
                var listResult = getPropertiesDeferred.await()
                _status.value = "Success: ${listResult.size} User properties retrieved"
                usersLiveData.value = listResult
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

    fun getItems() : LiveData<List<UserDetails>> = usersLiveData

    override fun onItemClick(users: List<UserDetails>, position: Int) {}
}
