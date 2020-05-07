package com.example.mvvm_project.overview

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

import com.example.mvvm_project.R
import com.example.mvvm_project.models.UserDetails

class OverviewFragment : Fragment() {

    private lateinit var adapter: UserAdapter
    lateinit var overviewViewModel: OverviewViewModel

    companion object {
        fun newInstance() = OverviewFragment()
    }

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.overview_fragment, container, false)
        adapter = UserAdapter()
        viewModel.display(list = UserDetails)

        overviewViewModel.getItems().observe(this, Observer { UserAdapter. }) //list

        val rv = view?.findViewById<RecyclerView>(R.id.recyclerView_list)
        rv?.adapter = UserAdapter()
        return view

    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}
