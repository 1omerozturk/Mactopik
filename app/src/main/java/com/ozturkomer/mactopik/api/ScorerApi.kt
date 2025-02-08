package com.ozturkomer.mactopik.api

import com.ozturkomer.mactopik.utils.ScorerResponse
import retrofit2.http.GET

interface ScorerApi {
     @GET("/top-scorers")
     suspend fun getScores(): ScorerResponse
}
