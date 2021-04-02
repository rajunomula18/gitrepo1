package com.example.gitrepo.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitrepo.model.gitmodel
import com.example.gitrepo.network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class RecyclerActivityViewModel(private val retroService: RetroService) : ViewModel() {
    var recyclerListData: MutableLiveData<gitmodel> = MutableLiveData()

    fun getRecyclerListDataObserver(): MutableLiveData<gitmodel> {
        return recyclerListData
    }

    fun makeApiCall(input: String) {
            retroService.getDataFromAPI(input).enqueue(object : Callback<gitmodel> {
            override fun onResponse(call: Call<gitmodel>, response: Response<gitmodel>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }
            override fun onFailure(call: Call<gitmodel>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }
}