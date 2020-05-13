package com.example.mvvm_project.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.paging.PagedList
import androidx.viewpager.widget.ViewPager

import com.example.mvvm_project.R
import com.example.mvvm_project.databinding.DetailFragmentBinding
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


        val users = arguments?.let { DetailFragmentArgs.fromBundle(it).selected }
        val detailViewModelFactory = application?.let {
            if (users != null) {
                DetailViewModelFactory(users, it)
            }
        }
        detailViewModel = ViewModelProviders.of(this, detailViewModelFactory).get(DetailViewModel::class.java)
        sharedModel = activity.run { ViewModelProviders.of(this!!).get(OverviewViewModel::class.java) }

        viewPager = view.findViewById(R.id.viewPager)

        detailViewModel.setList(sharedModel.usersLiveData.value as PagedList<UserDetails>)
        pagerAdapter = FragmentPagerAdapter(detailViewModel.pagedList.value!!)
        pagerAdapter.notifyDataSetChanged()

        viewPager.adapter = pagerAdapter
        viewPager.currentItem = detailViewModel.getSelectedValue()
        //user can swipe to see other repo details now
        return view
    }
}
