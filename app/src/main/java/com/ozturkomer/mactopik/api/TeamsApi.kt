package com.ozturkomer.mactopik.api


import com.ozturkomer.mactopik.utils.Fixture
import com.ozturkomer.mactopik.utils.Player
import com.ozturkomer.mactopik.utils.Teams
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamApi {
    // Tüm takımları listeleme
    @GET("get-teams")
    suspend fun getTeams(): List<Teams>

    // Belirli bir takımın kadrosunu alma
    @GET("get-teams/lineup/{teamId}")
    suspend fun getTeamLineUp(
        @Path("teamId") teamId: String
    ): List<Player> // Kadro için örnek bir veri sınıfı olan `Player` kullanıldı.

    // Belirli bir takımın fikstürünü alma
    @GET("get-teams/fixture/{teamId}")
    suspend fun getTeamFixture(
        @Path("teamId") teamId: String
    ): List<Fixture> // Fikstür için örnek bir veri sınıfı olan `Fixture` kullanıldı.
}