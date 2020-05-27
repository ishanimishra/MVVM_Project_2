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

/*
@Parcelize
@Entity
data class UserDetails(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "Full_Name") val full_name: String,
    val owner: Owner,
    @ColumnInfo(name = "Fork") val fork: Boolean,
    @ColumnInfo(name = "Description") val description: String?
) : Parcelable

@Parcelize
@Entity
data class Owner(
    val login: String,
    @ColumnInfo(name = "ImageURL") val avatar_url: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "Site_Admin") val site_admin: Boolean
) : Parcelable
 */