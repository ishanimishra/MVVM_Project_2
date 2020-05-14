package com.example.mvvm_project.paging

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import com.example.mvvm_project.models.UserDetails
import com.example.mvvm_project.network.UsersApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//class PagedDataSrc(private val scope: CoroutineScope) : PageKeyedDataSource<Int, UserDetails>() {
//    override fun loadInitial(
//        params: LoadInitialParams<Int>,
//        callback: LoadInitialCallback<Int, UserDetails>
//    ) {
//
//        scope.launch {
//            try {
//                val response = UsersApi.retrofitService.getP(0)
//                when {
//                    response.isSuccessful -> {
//                        val listing = response.body()
//                        callback.onResult(listing ?: listOf(), null, 1)
//                    }
//                }
//            } catch (e: Exception) {
//
//            }
//        }
//    }
//
//    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UserDetails>) {
//
//        scope.launch {
//            try {
//                val response = UsersApi.retrofitService.getP(params.key)
//                when {
//                    response.isSuccessful -> {
//                        val listing = response.body()
//
//
//                        callback.onResult(listing ?: listOf(), params.key.inc())
//                    }
//                }
//
//            } catch (e: java.lang.Exception) { }
//
//        }
//    }
//}
//
//
////have to implement
//
//
//override fun loadBefore(params: ItemKeyedDataSource.LoadParams<Int>, callback: ItemKeyedDataSource.LoadCallback<Int, UserDetails>) {
//    //have to implement
//
//    scope.launch {
//        try {
//            val response = UsersApi.retrofitService.getP(0)
//            when {
//                response.isSuccessful -> {
//                    val listing = response.body()
//
//
//                    callback.onResult(listing ?: listOf(), params.key.dec())
//                }
//            }
//
//        } catch (e: java.lang.Exception) {
//
//            Log.e("PostsDataSource", "Failed to fetch data!")
//
//        }
//
//    }
//
//}
//
//override fun invalidate() {
//    super.invalidate()
//    scope.cancel()
//}
//
//
//}
//
//
//
