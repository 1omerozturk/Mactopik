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

    val _isLoading=MutableStateFlow(true)
    val isLoading:StateFlow<Boolean> get()=_isLoading;

    private val api = Retrofit.Builder()
//        .baseUrl("https://back-end-z2ts.onrender.com/")
        .baseUrl("http://192.168.137.1:5080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MatchApi::class.java)

    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        viewModelScope.launch {
            try {
                _matches.value = api.getMatches()
                println(api.getMatches())
            } catch (e: Exception) {
                e.printStackTrace()
            }
            finally {
                _isLoading.value=false;
            }
        }
    }
}
