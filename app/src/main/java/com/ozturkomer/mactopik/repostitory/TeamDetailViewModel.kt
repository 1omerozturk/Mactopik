package com.ozturkomer.mactopik.repostitory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ozturkomer.mactopik.api.TeamApi
import com.ozturkomer.mactopik.utils.Fixture
import com.ozturkomer.mactopik.utils.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeamDetailViewModel(teamId: String) : ViewModel() {
    constructor():this("")
    private val _fixtures = MutableStateFlow<List<Fixture>>(emptyList())
    val fixtures: StateFlow<List<Fixture>> get() = _fixtures

    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> get() = _players

    private val _isLoadingFixtures = MutableStateFlow(false)
    val isLoadingFixtures: StateFlow<Boolean> get() = _isLoadingFixtures

    private val _isLoadingPlayers = MutableStateFlow(false)
    val isLoadingPlayer: StateFlow<Boolean> get() = _isLoadingPlayers

    private val api = Retrofit.Builder()
        .baseUrl("http://192.168.137.1:5080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TeamApi::class.java)
    init{
        fetchFixtures(teamId)
        fetchPlayers(teamId)
    }

    fun fetchFixtures(teamId: String) {
        viewModelScope.launch {
            try {
                _isLoadingFixtures.value=true
                val fixtureList = api.getTeamFixture(teamId)
                _fixtures.value = fixtureList
            } catch (e: Exception) {
                e.printStackTrace() // Hata loglama
                _fixtures.value = emptyList()
            } finally {
                _isLoadingFixtures.value = false
            }
        }
    }

    fun fetchPlayers(teamId: String) {
        viewModelScope.launch {
            try {
                _isLoadingPlayers.value=true
                val playerList = api.getTeamLineUp(teamId)
                _players.value = playerList
            } catch (e: Exception) {
                e.printStackTrace() // Hata loglama
                _players.value = emptyList()
            } finally {
                _isLoadingPlayers.value = false
            }
        }
    }
}