package com.example.mvvm_project.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mvvm_project.models.UserDetails

class DetailViewModel(userDetails : UserDetails, application: Application) : AndroidViewModel(application) {

    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int>
        get() = _position

    private val _selected = MutableLiveData<UserDetails>()
    val selected: LiveData<UserDetails>
        get() = _selected

    private val _pagedList = MutableLiveData<PagedList<UserDetails>>()
    val pagedList: LiveData<PagedList<UserDetails>>
        get() = _pagedList

    init {
        _selected.value = userDetails
    }

    fun setList(list: PagedList<UserDetails>) {
        _pagedList.value = list
    }

    fun getSelectedValue(): Int {
        _position.value = _pagedList.value!!.indexOf(_selected.value)
        return position.value!!
    }
}
