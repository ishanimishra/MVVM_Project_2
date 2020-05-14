package com.example.mvvm_project.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mvvm_project.models.UserDetails
import java.util.ArrayList

class DetailViewModel(userDetails: ArrayList<UserDetails>?, application: Application) : ViewModel() {

    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int>
        get() = _position

    private val _selected = MutableLiveData<ArrayList<UserDetails>>()
    val selected: LiveData<ArrayList<UserDetails>>
        get() = _selected

    init {
        _selected.value = userDetails
    }
}
