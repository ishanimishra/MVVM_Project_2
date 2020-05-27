package com.example.mvvm_project.network

import androidx.core.content.contentValuesOf
import com.example.mvvm_project.models.UserDetails
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//private const val BASE_URL = "https://api.github.com/"
private const val BASE_URL = "http://www.mocky.io/v2/"
/* APIs
Page 1 - http://www.mocky.io/v2/5ec26e442f0000b750c352a1
page 2 - http://www.mocky.io/v2/5ec26ec52f0000b750c352ac
page 3 - http://www.mocky.io/v2/5ec26f122f00007aa8c352b0
page 4 - http://www.mocky.io/v2/5ec26f492f00001fa3c352b2
page 5 - http://www.mocky.io/v2/5ec26f822f00007aa8c352b6
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface UsersApiService {
    @GET("5ec26e442f0000b750c352a1")
    fun getProperties1Async():
            Deferred<List<UserDetails>>

    @GET("5ec26ec52f0000b750c352ac")
    fun getProperties2Async():
            Deferred<List<UserDetails>>

    @GET("5ec26f122f00007aa8c352b0")
    fun getProperties3Async():
            Deferred<List<UserDetails>>

    @GET("5ec26f492f00001fa3c352b2")
    fun getProperties4Async():
            Deferred<List<UserDetails>>

    @GET("5ec26f822f00007aa8c352b6")
    fun getProperties5Async():
            Deferred<List<UserDetails>>
    
    // for response
    // fun getP(@Query("since") since: Int) : Response<List<UserDetails>>
}
//a public object called UsersApi to expose the Retrofit service to the rest of the app
object UsersApi {
    val retrofitService: UsersApiService by lazy {
        retrofit.create(UsersApiService::class.java)
    }
}