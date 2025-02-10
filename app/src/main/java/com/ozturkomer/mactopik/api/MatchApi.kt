package com.ozturkomer.mactopik.api

import com.ozturkomer.mactopik.utils.Match
import com.ozturkomer.mactopik.utils.MatchDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchApi {
    @GET("get-matches")
    suspend fun getMatches(): List<Match>

    @GET("get-matches/{id}")
    suspend fun getMatchesWeek(
        @Path("id") weekId: String
    ): List<Match>

    @GET("get-matches/{week}/{id}")
    suspend fun getMatchDetail(
        @Path("week") week: String,
        @Path("id") id: Int,
    ): MatchDetail
}
