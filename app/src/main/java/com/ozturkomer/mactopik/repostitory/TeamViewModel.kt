package com.ozturkomer.mactopik.repostitory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozturkomer.mactopik.api.TeamApi
import com.ozturkomer.mactopik.utils.Teams
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TeamViewModel:ViewModel(){

    private val _teams= MutableStateFlow<List<Teams>>(emptyList())
    val teams:StateFlow<List<Teams>> get()=_teams

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val api = Retrofit.Builder()
        .baseUrl("https://superlig-api.onrender.com/")
//        .baseUrl("http://192.168.1.102:5080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TeamApi::class.java)

    init{
        fetchTeams()
    }

    fun fetchTeams(){
        viewModelScope.launch {
            try {
                _isLoading.value=true
                _teams.value=api.getTeams()

            }
            catch (e:Exception)
            {
                e.printStackTrace()
            }
            finally {
                _isLoading.value=false
            }
        }
    }

}