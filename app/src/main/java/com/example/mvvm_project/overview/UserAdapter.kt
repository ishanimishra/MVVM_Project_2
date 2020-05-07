package com.example.mvvm_project.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_project.R
import com.example.mvvm_project.models.UserDetails
import kotlinx.android.synthetic.main.list.view.*

class UserAdapter() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var users: List<UserDetails> = ArrayList<UserDetails>()
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val user = users[position]
        holder.initialize(user)
    }


    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val UserName = view.name
        val UserLogin = view.login
        val UserDescription = view.description
        val imgUrl = view.imageView

        fun initialize(users: UserDetails) {
//            bindImage(imgUrl, users.owner.avatar_url)
            UserName.text = "Name: " + users.name
            UserLogin.text = "Login: " + users.owner.login
            UserDescription.text = "Description: " + users.description

        }

    }
}

