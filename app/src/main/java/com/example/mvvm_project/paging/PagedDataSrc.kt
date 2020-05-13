package com.example.mvvm_project.paging

//import android.util.Log
//import androidx.paging.PageKeyedDataSource
//import com.example.mvvm_project.models.UserDetails
//import com.example.mvvm_project.network.UsersApi
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.cancel
//import kotlinx.coroutines.launch
//import java.lang.Exception
//
//class PagedDataSrc(private val scope: CoroutineScope) : PageKeyedDataSource<Int, UserDetails>() {
//    override fun loadInitial(
//        params: LoadInitialParams<Int>,
//        callback: LoadInitialCallback<Int, UserDetails>
//    ) {
//
//        scope.launch {
//            try {
//                val response = UsersApi.retrofitService.getProperties(0)
//                when {
////have to implement
//                }
//            }catch (e : Exception) {
//
//            }
//        }
//    }
//
//    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UserDetails>) {
//        val response = UsersApi.retrofitService.getProperties(params.key)
//        when {
//
//        }
//    //have to implement
//    }
//
//    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UserDetails>) {
//    //have to implement
//        val response = UsersApi.retrofitService.getProperties(0)
//        when {
//
//        }
//    }
//
//    override fun invalidate() {
//        super.invalidate()
//        scope.cancel()
//    }
//
//
//}
//
//
//
