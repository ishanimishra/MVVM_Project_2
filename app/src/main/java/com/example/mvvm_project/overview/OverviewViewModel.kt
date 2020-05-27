package com.example.mvvm_project.overview

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_project.models.UserDetails
import com.example.mvvm_project.network.UsersApi
import kotlinx.coroutines.*

class OverviewViewModel() : ViewModel(), onUserClickListener {

    val usersLiveData = MutableLiveData<List<UserDetails>>()

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _property = MutableLiveData<UserDetails>()
    val property : LiveData<UserDetails>
    get() = _property

    var isLoading = MutableLiveData<Boolean>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    public fun getUserProperties(pageNum : Int) {
        var getPropertiesDeferred : Deferred<List<UserDetails>>? = null

        coroutineScope.launch {
            //switch
            when (pageNum) {
                1 -> { getPropertiesDeferred = UsersApi.retrofitService.getProperties1Async() }
                2 -> { getPropertiesDeferred = UsersApi.retrofitService.getProperties2Async() }
                3 -> { getPropertiesDeferred = UsersApi.retrofitService.getProperties3Async() }
                4 -> { getPropertiesDeferred = UsersApi.retrofitService.getProperties4Async() }
                5 -> { getPropertiesDeferred = UsersApi.retrofitService.getProperties5Async() }
                else -> {
                   // var getPropertiesDeferred = null
                    //Toast.makeText(this,"List end reached",Toast.LENGTH_SHORT).show()
                }
            }

            try {
                var listResult = getPropertiesDeferred?.await()
                _status.value = "Success: ${listResult?.size} User properties retrieved"

                //check if already value present, append list

                usersLiveData.value = listResult

//                if (usersLiveData != null) {
//                    usersLiveData.value?.plus(listResult)
//                }
//                else {
//                    usersLiveData.value = listResult
//                }

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
