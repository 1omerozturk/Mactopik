package com.ozturkomer.mactopik.repostitory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TeamDetailViewModelFactory(private val teamId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TeamDetailViewModel(teamId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
