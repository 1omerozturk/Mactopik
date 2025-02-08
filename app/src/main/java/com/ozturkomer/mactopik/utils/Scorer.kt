package com.ozturkomer.mactopik.utils

data class ScorerResponse(
    val scorers: List<Scorer>,
    val lastUpdated: String
)

data class Scorer(
    val rank: Int,
    val playerName: String,
    val teamName: String,
    val goals: Int,
    val teamLogo: String
)
