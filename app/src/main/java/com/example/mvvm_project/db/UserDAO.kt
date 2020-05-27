package com.example.mvvm_project.db
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import com.example.mvvm_project.models.UserDetails
//
//@Dao
//interface UserDAO {
//
//    @Insert
//    fun saveUser(user: UserDetails)
//
//    @Query("Select * from UserDetails")
//    fun readUser() : LiveData<List<UserDetails>>
//}