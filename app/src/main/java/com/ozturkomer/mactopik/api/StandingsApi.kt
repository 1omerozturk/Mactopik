package com.ozturkomer.mactopik.api

import com.ozturkomer.mactopik.utils.StandingsResponse
import retrofit2.http.GET

interface StandingsApi {
    @GET("standings")
    suspend fun getStandings(): StandingsResponse
}
