package com.example.mvvm_project.overview


import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import com.example.mvvm_project.db.UserDAO
import com.example.mvvm_project.db.appDB
import com.example.mvvm_project.models.UserDetails
import com.example.mvvm_project.network.UsersApi
import kotlinx.coroutines.*

//class OverviewViewModel() : ViewModel(), onUserClickListener {
//
//
//    var usersLiveData = MutableLiveData<List<UserDetails>>()
//
//
//    private val _status = MutableLiveData<String>()
//    val status: LiveData<String>
//        get() = _status
//
//    private val _property = MutableLiveData<UserDetails>()
//    val property: LiveData<UserDetails>
//        get() = _property
//
//
//    private var viewModelJob = Job()
//    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//
//    public fun getUserProperties(pageNum: Int) {
//        var getPropertiesDeferred: Deferred<List<UserDetails>>? = null
//
//        coroutineScope.launch {
//            //switch
//            when (pageNum) {
//                1 -> {
//                    getPropertiesDeferred = UsersApi.retrofitService.getProperties1Async()
//                }
//                2 -> {
//                    getPropertiesDeferred = UsersApi.retrofitService.getProperties2Async()
//                }
//                3 -> {
//                    getPropertiesDeferred = UsersApi.retrofitService.getProperties3Async()
//                }
//                4 -> {
//                    getPropertiesDeferred = UsersApi.retrofitService.getProperties4Async()
//                }
//                5 -> {
//                    getPropertiesDeferred = UsersApi.retrofitService.getProperties5Async()
//                }
//                else -> {
//                    // var getPropertiesDeferred = null
//                    //Toast.makeText(this,"List end reached",Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            try {
//                var listResult = getPropertiesDeferred?.await()
//                _status.value = "Success: ${listResult?.size} User properties retrieved"
//
//                usersLiveData.value = listResult
//
//
//            } catch (e: Exception) {
//                _status.value = "Failure: ${e.message}"
//            }
//        }
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        viewModelJob.cancel()
//    }
//
//    fun display(list: UserDetails) {
//        _property.value = list
//    }
//
//    fun getItems(): LiveData<List<UserDetails>> = usersLiveData
//
//    override fun onItemClick(users: List<UserDetails>, position: Int) {}
//}

class OverviewViewModel(application: Application) : AndroidViewModel(application), onUserClickListener {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _property = MutableLiveData<UserDetails>()
    val property: LiveData<UserDetails>
        get() = _property

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    public fun getUserProperties(pageNum: Int) {
        var getPropertiesDeferred: Deferred<List<UserDetails>>? = null

        coroutineScope.launch {
            //switch
            when (pageNum) {
                1 -> {
                    getPropertiesDeferred = UsersApi.retrofitService.getProperties1Async()
                }
                2 -> {
                    getPropertiesDeferred = UsersApi.retrofitService.getProperties2Async()
                }
                3 -> {
                    getPropertiesDeferred = UsersApi.retrofitService.getProperties3Async()
                }
                4 -> {
                    getPropertiesDeferred = UsersApi.retrofitService.getProperties4Async()
                }
                5 -> {
                    getPropertiesDeferred = UsersApi.retrofitService.getProperties5Async()
                }
                else -> {
                    // var getPropertiesDeferred = null
                    //Toast.makeText(this,"List end reached",Toast.LENGTH_SHORT).show()
                }
            }

            try {
                var listResult = getPropertiesDeferred?.await()
                _status.value = "Success: ${listResult?.size} User properties retrieved"

                //usersLiveData.value = listResult
                if (listResult != null) {
                    updateUsers(listResult)
                }


            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    private suspend fun updateUsers(users: List<UserDetails>) {
        var usersDao1 = getUserDao()
        usersDao1.saveUser(users)
    }

    internal fun getUserDao(): UserDAO {
        val userDAO = appDB.getDatabase(getApplication(), viewModelScope).userDAO()
        return userDAO
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun display(list: UserDetails) {
        _property.value = list
    }

    override fun onItemClick(users: List<UserDetails>, position: Int) {}
}
