package com.ozturkomer.mactopik.repostitory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozturkomer.mactopik.api.MatchApi
import com.ozturkomer.mactopik.utils.MatchDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MatchDetailViewModel(week: String, id: String) : ViewModel() {
    private val _details = MutableStateFlow<MatchDetail?>(null)
    val details: StateFlow<MatchDetail?> get() = _details

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading;


    private val api = Retrofit.Builder()
        .baseUrl("https://superlig-api.onrender.com/")
//        .baseUrl("http://192.168.137.1:5080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MatchApi::class.java)

    init {
        println("week: " + week + "\nid: " + id)
        fetchMatchDetail(week, id) // Varsayılan değerler kullanıldı
    }

    fun fetchMatchDetail(week: String, id: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _details.value = api.getMatchDetail(week, id)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
