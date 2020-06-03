package com.example.mvvm_project.overview

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
//import com.example.mvvm_project.db.UserDAO
//import com.example.mvvm_project.db.UserDAO
//import com.example.mvvm_project.db.UserRepository
//import com.example.mvvm_project.db.appDB
import com.example.mvvm_project.models.UserDetails
import com.example.mvvm_project.network.UsersApi
import kotlinx.coroutines.*

//class OverviewViewModel(val database: UserDAO ,application: Application) : AndroidViewModel(application), onUserClickListener {
//
//    //private val repository : UserRepository
//    var usersLiveData = MutableLiveData<List<UserDetails>>()
//
//    //    private val UserDatabase : appDB? = null
////    private val userDao : UserDAO? = null
//    private val _status = MutableLiveData<String>()
//    val status: LiveData<String>
//        get() = _status
//
//    private val _property = MutableLiveData<UserDetails>()
//    val property : LiveData<UserDetails>
//        get() = _property
//
//
////    init {
////        val wordsDao = appDB.getDatabase(application).userDAO()
////        repository = UserRepository(wordsDao)
////        usersLiveData = repository.allUsers as MutableLiveData<List<UserDetails>>
////    }
//
//    private var viewModelJob = Job()
//    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//    public fun getUserProperties(pageNum : Int) {
//        var getPropertiesDeferred : Deferred<List<UserDetails>>? = null
//
//        coroutineScope.launch {
//            //switch
//            when (pageNum) {
//                1 -> { getPropertiesDeferred = UsersApi.retrofitService.getProperties1Async() }
//                2 -> { getPropertiesDeferred = UsersApi.retrofitService.getProperties2Async() }
//                3 -> { getPropertiesDeferred = UsersApi.retrofitService.getProperties3Async() }
//                4 -> { getPropertiesDeferred = UsersApi.retrofitService.getProperties4Async() }
//                5 -> { getPropertiesDeferred = UsersApi.retrofitService.getProperties5Async() }
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
//    //Created a wrapper insert() method that calls the Repository's insert() method.
//    // In this way, the implementation of insert() is encapsulated from the UI.
//    // We don't want insert to block the main thread,
//    // so we're launching a new coroutine and calling the repository's insert,
//    // which is a suspend function.
//    // As mentioned, ViewModels have a coroutine scope based on their life cycle called viewModelScope, which we use here.
////    fun insert(userDetails: UserDetails) = viewModelScope.launch(Dispatchers.IO) {
////        repository.insert(userDetails)
////    }
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
//    fun getItems() : LiveData<List<UserDetails>> = usersLiveData
//
//    override fun onItemClick(users: List<UserDetails>, position: Int) {}
//
//
//
//}

class OverviewViewModel() : ViewModel(), onUserClickListener {

    //private val repository : UserRepository
    var usersLiveData = MutableLiveData<List<UserDetails>>()

//    private val UserDatabase : appDB? = null
//    private val userDao : UserDAO? = null
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _property = MutableLiveData<UserDetails>()
    val property : LiveData<UserDetails>
    get() = _property


//    init {
//        val wordsDao = appDB.getDatabase(application).userDAO()
//        repository = UserRepository(wordsDao)
//        usersLiveData = repository.allUsers as MutableLiveData<List<UserDetails>>
//    }

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

                usersLiveData.value = listResult


            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    //Created a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is encapsulated from the UI.
    // We don't want insert to block the main thread,
    // so we're launching a new coroutine and calling the repository's insert,
    // which is a suspend function.
    // As mentioned, ViewModels have a coroutine scope based on their life cycle called viewModelScope, which we use here.
//    fun insert(userDetails: UserDetails) = viewModelScope.launch(Dispatchers.IO) {
//        repository.insert(userDetails)
//    }

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
