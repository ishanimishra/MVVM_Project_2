package com.example.mvvm_project.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDetails(
    val name: String,
    val full_name: String,
    val owner: Owner,
    val fork: Boolean,
    val description: String?
) : Parcelable

@Parcelize
data class Owner(
    val login: String,
    val avatar_url: String,
    val type: String,
    val site_admin: Boolean
) : Parcelable