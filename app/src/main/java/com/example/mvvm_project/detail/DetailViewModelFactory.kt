package com.example.mvvm_project.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_project.models.UserDetails
import java.util.ArrayList

class DetailViewModelFactory(
    private val users: ArrayList<UserDetails>?, private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(users, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}