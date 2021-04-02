package com.example.gitrepo.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitrepo.network.RetroService

class FactoryViewModel(private val retroService: RetroService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecyclerActivityViewModel::class.java)) {
            return RecyclerActivityViewModel(retroService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}