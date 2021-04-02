package com.example.gitrepo.ui

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.gitrepo.R
import com.example.gitrepo.Adapter.RecyclerViewAdapter
import com.example.gitrepo.model.Item
import com.example.gitrepo.model.gitmodel
import com.example.gitrepo.network.RetroInstance
import com.example.gitrepo.viewModels.FactoryViewModel
import com.example.gitrepo.viewModels.RecyclerActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    companion object {
        private const val KEY = "naruto"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        createData()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter

            val decoration =
                DividerItemDecoration(this@MainActivity, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }
    }

    private fun createData() {
        val viewModel =
            ViewModelProviders.of(this, FactoryViewModel(RetroInstance.getRetroInstance()))
                .get(RecyclerActivityViewModel::class.java)
        //  default is takes "naruto"
        viewModel.makeApiCall(KEY)

        viewModel.getRecyclerListDataObserver().observe(this, Observer<gitmodel> {
            if (it != null) {
                recyclerViewAdapter.setListData(it.items as ArrayList<Item>)
                recyclerViewAdapter.notifyDataSetChanged()

            } else {
                displayToast(getString(R.string.error_msg))
            }

        })
        searchButton.setOnClickListener {
            hideSoftKeyboard()
            if (searchBoxId.text.isNotBlank()) {
                viewModel.makeApiCall(searchBoxId.text.toString())
            } else {
                displayToast(getString(R.string.error_search))
            }
        }
    }

    private fun hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager =
                ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun displayToast(text: String) {
        Toast.makeText(
            this@MainActivity,
            text,
            Toast.LENGTH_LONG
        ).show()
    }
}