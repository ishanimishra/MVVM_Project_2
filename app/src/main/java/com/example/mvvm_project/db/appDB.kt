package com.example.mvvm_project.db
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.mvvm_project.models.UserDetails
//
//@Database (entities = [(UserDetails::class)], version = 1)
//abstract  class appDB : RoomDatabase() {
//    abstract fun userDAO() : UserDAO
//
//    companion object {
//        @Volatile
//        private var INSTANCE: appDB? = null
//
//        fun getDatabase(context: Context): appDB {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    appDB::class.java,
//                    "user_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
//}