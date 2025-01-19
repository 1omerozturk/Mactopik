package com.ozturkomer.mactopik.api

import com.ozturkomer.mactopik.utils.Match
import retrofit2.http.GET

interface MatchApi {
    @GET("get-matches")
    suspend fun getMatches(): List<Match>
}
