package com.ozturkomer.mactopik.repostitory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozturkomer.mactopik.api.MatchApi
import com.ozturkomer.mactopik.utils.Match
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MatchViewModel : ViewModel() {
    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> get() = _matches

    private val _week = MutableStateFlow(0)
    val week: StateFlow<Int> get() = _week


    val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading;

    private val api = Retrofit.Builder()
        .baseUrl("https://superlig-api.onrender.com/")
//        .baseUrl("http://192.168.137.1:5080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MatchApi::class.java)

    init {
        fetchMatches()
    }

    fun fetchMatches(week: String? = null) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _matches.value = if (week == null) {
                    api.getMatches()
                } else {
                    api.getMatchesWeek(week)
                }
                if (week != null) {
                    _week.value = week.toInt()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
