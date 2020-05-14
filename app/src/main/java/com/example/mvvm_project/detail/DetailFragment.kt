package com.example.mvvm_project.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
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
    private var userDetail: UserDetails? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the view
        val view = inflater.inflate(R.layout.detail_fragment, container, false)
        val application = activity?.application


        userDetail = arguments?.let { DetailFragmentArgs.fromBundle(it).selected }
        val detailViewModelFactory = DetailViewModelFactory(userDetail, application!!)
//        val detailViewModelFactory = application?.let {
//            if (users != null) {
//                DetailViewModelFactory(users, it)
//            }
//        }
        detailViewModel = ViewModelProviders.of(this, detailViewModelFactory).get(DetailViewModel::class.java)
        sharedModel = activity.run { ViewModelProviders.of(this!!).get(OverviewViewModel::class.java) }


        sharedModel.getItems().observe(viewLifecycleOwner, Observer {
            detailViewModel.setList(sharedModel.getItems().value as PagedList<UserDetails>)
        })

        //detailViewModel.setList(sharedModel.usersLiveData.value as PagedList<UserDetails>)
        var arrayList = ArrayList<UserDetails>()
        arrayList.add(userDetail!!)
        viewPager = view.findViewById(R.id.viewPager)
//        pagerAdapter = FragmentPagerAdapter(arrayList)
        viewPager.adapter = pagerAdapter
        pagerAdapter.notifyDataSetChanged()
        viewPager.currentItem = detailViewModel.getSelectedValue()

//        detailViewModel.pagedList.observe(viewLifecycleOwner, Observer {
//
//        })
        //pagerAdapter = FragmentPagerAdapter(detailViewModel.pagedList.value!!)
        //user can swipe to see other repo details now
        return view
    }
}
