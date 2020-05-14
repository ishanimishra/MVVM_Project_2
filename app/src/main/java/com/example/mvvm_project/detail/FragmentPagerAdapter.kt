package com.example.mvvm_project.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedList
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.mvvm_project.R
import com.example.mvvm_project.models.UserDetails

class FragmentPagerAdapter(val users: PagedList<UserDetails>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return users.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.detail_item_view, container, false)

        users.loadAround(position)

        //loading image in the view
        val imgURL = users[position]?.owner?.avatar_url
        val imgView = view.findViewById<ImageView>(R.id.imageView)
        Glide.with(container.context).load(imgURL).into(imgView)

        //loading remaining
        val name1 = view.findViewById<TextView>(R.id.name)
        name1.text = "Name : " + users[position]?.name

        val fullname1 = view.findViewById<TextView>(R.id.full_name)
        fullname1.text = "Full Name : " + users[position]?.full_name

        val fork1 = view.findViewById<TextView>(R.id.fork)
        fork1.text = "Fork : " + users[position]?.fork

        val description1 = view.findViewById<TextView>(R.id.description)
        description1.text = "Description : " + users[position]?.description

        val login1 = view.findViewById<TextView>(R.id.login)
        login1.text = "Login : " + users[position]?.owner?.login

        val type1 = view.findViewById<TextView>(R.id.type)
        type1.text = "Type : " + users[position]?.owner?.type

        val siteadmin1 = view.findViewById<TextView>(R.id.site_admin)
        siteadmin1.text = "Site Admin : " + users[position]?.owner?.site_admin

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}