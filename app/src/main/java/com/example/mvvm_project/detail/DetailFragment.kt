package com.example.mvvm_project.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.viewpager.widget.ViewPager
import com.example.mvvm_project.R
import com.example.mvvm_project.models.UserDetails
import com.example.mvvm_project.overview.OverviewViewModel


class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: FragmentPagerAdapter
    private lateinit var sharedModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the view
        val view = inflater.inflate(R.layout.detail_fragment, container, false)
        val application = activity?.application

        //fetching arguments sent from overview fragment in bundle
        val userList = requireArguments().getParcelableArrayList<UserDetails>("key")
        val pos = requireArguments().getInt("position")

        val detailViewModelFactory = DetailViewModelFactory(userList, application!!)

        detailViewModel = ViewModelProviders.of(this, detailViewModelFactory).get(DetailViewModel::class.java)
        sharedModel = activity.run { ViewModelProviders.of(this!!).get(OverviewViewModel::class.java) }

        viewPager = view.findViewById(R.id.viewPager)
        pagerAdapter = FragmentPagerAdapter(userList!!)
        viewPager.adapter = pagerAdapter
        viewPager.setCurrentItem(pos)
        pagerAdapter.notifyDataSetChanged()

        return view
    }
}
