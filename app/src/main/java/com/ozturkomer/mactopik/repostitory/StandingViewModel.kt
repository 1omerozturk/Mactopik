package com.ozturkomer.mactopik.repostitory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozturkomer.mactopik.api.MatchApi
import com.ozturkomer.mactopik.api.StandingsApi
import com.ozturkomer.mactopik.utils.Standing
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StandingsViewModel : ViewModel() {
    private val _standings = MutableStateFlow<List<Standing>>(emptyList())
    val standings: StateFlow<List<Standing>> get() = _standings

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val api = Retrofit.Builder()
//        .baseUrl("https://back-end-z2ts.onrender.com/")
        .baseUrl("http://192.168.137.1:5080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StandingsApi::class.java)

    init {
        fetchStandings()
    }

    private fun fetchStandings() {
        viewModelScope.launch {
            try {
                val response = api.getStandings()
                _standings.value = response.standings
                println(response.standings)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                // yükleme tamamlandı ve animasyonu kapat
                _isLoading.value = false
            }
        }
    }
}
