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
        val rv = view?.findViewById<RecyclerView>(R.id.recyclerView_list)
        adapter = UserAdapter()
        rv?.adapter = adapter

        viewModel.getItems().observe(viewLifecycleOwner, Observer {
            //Callback received only some data present
            adapter.users = it
            adapter.notifyDataSetChanged()
        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getUserProperties()

    }

}
