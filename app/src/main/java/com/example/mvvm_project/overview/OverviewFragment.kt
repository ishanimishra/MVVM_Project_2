package com.example.mvvm_project.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_project.R
import com.example.mvvm_project.models.UserDetails


class OverviewFragment() : Fragment() {

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
        rv?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter = UserAdapter(this)
        rv?.adapter = adapter

        addDataToList()

        rv?.addOnScrollListener(object :
            PaginationScrollListener(
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            ) {
            override fun onLoadMore() {
                addDataToList()
            }
        })

        viewModel.getItems().observe(viewLifecycleOwner, Observer {
            //Callback received only some data present
            adapter.users = it
            adapter.notifyDataSetChanged()
        })

        return view
    }

    private fun addDataToList() {
        val progressBar = view?.findViewById<ProgressBar>(R.id.progress)
        progressBar?.visibility = View.VISIBLE

        //add data from consecutive api

        adapter.notifyDataSetChanged()
        progressBar?.visibility = View.GONE
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getUserProperties()
    }

    fun onItemClick(users: List<UserDetails>, adapterPosition: Int) {
        Toast.makeText(context, users[adapterPosition].name, Toast.LENGTH_SHORT).show()
        //passing argument in bundle to detail fragment on click of list item
        val bundle = Bundle()
        bundle.putParcelableArrayList("key", ArrayList<UserDetails>(users))
        bundle.putInt("position", adapterPosition)
        //navigate to detail fragment
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_overviewFragment_to_detailFragment, bundle)
        }
    }
}
