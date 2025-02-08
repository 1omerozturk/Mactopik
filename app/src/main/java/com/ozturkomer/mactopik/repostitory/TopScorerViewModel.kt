package com.ozturkomer.mactopik.repostitory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozturkomer.mactopik.api.ScorerApi
import com.ozturkomer.mactopik.utils.Scorer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TopScorerViewModel : ViewModel() {
    private val _scorers = MutableStateFlow<List<Scorer>>(emptyList())
    val scorers: StateFlow<List<Scorer>> get() = _scorers

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val api = Retrofit.Builder()
        .baseUrl("https://superlig-api.onrender.com/")
//        .baseUrl("http://192.168.137.1:5080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ScorerApi::class.java)

    init {
        fetchScorers()
    }

    fun fetchScorers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = api.getScores()
                _scorers.value = response.scorers.sortedBy { it.rank } // Sıralama yapılabilir
            } catch (e: Exception) {
                Log.e("TopScorerViewModel", "Error fetching scorers", e)
            } finally {
                _isLoading.value = false
            }
        }
    }


}