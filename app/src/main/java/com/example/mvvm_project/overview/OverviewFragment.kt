package com.example.mvvm_project.overview

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
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
    private lateinit var progressBar : ProgressBar


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
        progressBar = view?.findViewById<ProgressBar>(R.id.progress)!!
        val rv = view?.findViewById<RecyclerView>(R.id.recyclerView_list)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv?.layoutManager = layoutManager
        rv?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter = UserAdapter(this)
        rv?.adapter = adapter


        rv?.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun onLoadMore(pageNum: Int) {
                addDataToList(pageNum)
            }
        })
        

        viewModel.getItems().observe(viewLifecycleOwner, Observer {
            //Callback received only some data present
            if(it == null) progressBar?.visibility = View.GONE
            adapter.addList(it as ArrayList<UserDetails>)
            adapter.notifyDataSetChanged()
        })

        return view
    }

    private fun addDataToList(pageNumber: Int) {
        progressBar?.visibility = View.VISIBLE
        Handler().postDelayed(Runnable {
            viewModel.getUserProperties(pageNumber)
            progressBar?.visibility = View.INVISIBLE
        }, 1500)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //1st request
        viewModel.getUserProperties(1)
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
