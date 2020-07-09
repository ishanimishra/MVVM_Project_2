package com.example.mvvm_project.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvm_project.models.UserDetails

@Dao
interface UserDAO {

    @Insert
    suspend fun saveUser(user: List<UserDetails>)

    @Query("SELECT * FROM (SELECT * FROM user_database ORDER BY id DESC LIMIT 10) ORDER BY id ASC") //page wise read 10 at a time
    suspend fun readUser() : List<UserDetails>
}